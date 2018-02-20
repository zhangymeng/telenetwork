package cn.dyt.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.MenuDao;
import cn.dyt.dao.RoleDao;
import cn.dyt.dao.UserDao;
import cn.dyt.po.Menu;
import cn.dyt.po.Role;
import cn.dyt.po.UserInfo;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public Map<String, Object> login(UserVo vo) {
		boolean result = false;
		String reason = "";
		IndexVo inVo = new IndexVo();
		inVo.setUsername(vo.getUsername());
		UserInfo userInfo = userDao.getUserByUsername(inVo);
		if(userInfo!=null){
			if(userInfo.getPassword().equals(vo.getPassword())){
				result = true;
				reason = userInfo.getId().toString();
			}else{
				reason = "密码错误";
			}
		}else{
			reason = "该账号不存在";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public UserInfo getUserById(Integer id) {
		UserInfo userInfo = userDao.getUserById(id);
		return userInfo;
	}

	@Override
	public List<Menu> getMenu(Integer roleId) {
		List<Menu> menuList = new ArrayList<Menu>();
		Role role = roleDao.getRoleById(roleId);
		String userAccess =role.getMenuIds();
		String[] arr = null;
		if(userAccess!=null && userAccess!=""){
			arr= userAccess.split(",");
			List<Integer> list = new ArrayList<Integer>();
			if(arr.length>0){
				for(int i=0;i<arr.length;i++){
					Integer acc = Integer.parseInt(arr[i]);
					list.add(acc);
				}
			}
			menuList = menuDao.findAll(list);
			menuList = formatMenu(menuList);
		}
		return menuList;
	}
	
	public List<Menu> formatMenu(List<Menu> menuList){
		List<Menu> resList = new ArrayList<Menu>();
		for(Menu menu : menuList){
			if(menu.getpId()==0){
				//父节�?
				List<Menu> childList = new ArrayList<Menu>();
				for(Menu child : menuList){
					if(child.getpId()==menu.getId()){
						//子节�?
						childList.add(child);
					}
				}
				menu.setChild(childList);
				resList.add(menu);
			}
		}
		return resList;
	}

	@Override
	public Integer editUser(UserVo vo) {
		Integer count = userDao.editUser(vo);
		return count;
	}

	@Override
	public List<UserInfo> findAll(IndexVo vo) {
		List<UserInfo> list = userDao.findAll(vo);
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Integer count = userDao.del(vo);
		if(count>0){
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(IndexVo vo) {
		boolean result = false;
		String reason = "";
		UserInfo su = userDao.getUserByUsername(vo);
		if(su==null || (su!=null && su.getId()==vo.getId())){
			Integer count = 0;
			if(vo.getId()!=null){
				count = userDao.edit(vo);
			}else{
				Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
				vo.setCreateDate(createDate);
				count = userDao.add(vo);
			}
			if(count>0){
				result = true;
			}
		}else{
			reason = "学号已存在";
		}
		return Tools.resultMap(result, reason);
	}

}
