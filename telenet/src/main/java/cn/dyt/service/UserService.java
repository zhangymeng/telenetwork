package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Menu;
import cn.dyt.po.UserInfo;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

public interface UserService {

	Map<String, Object> login(UserVo vo);

	UserInfo getUserById(Integer id);

	List<Menu> getMenu(Integer roleId);

	Integer editUser(UserVo vo);

	List<UserInfo> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(IndexVo vo);

}
