package cn.dyt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.dyt.po.UserInfo;
import cn.dyt.service.UserService;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping("/editPwdPage")
	public ModelAndView editPwdPage(HttpServletRequest request,Integer roleId){
	    ModelMap model = new ModelMap();
	    model.addAttribute("roleId", roleId);
	    return new ModelAndView("editPwdPage", model);
	}
	
	/**
	 * 修改密码
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPwd")
	@ResponseBody
    public Map<String,Object> editPwd(UserVo vo,HttpServletRequest request){
		 Map<String,Object> map = new HashMap<String,Object>();
		 UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(vo.getPassword().equals(vo.getPasswords())){
				int id = user.getId();
				vo.setId(id);
				if(user.getPassword().equals(vo.getOldPassword())){
					Integer count = userService.editUser(vo);
					if(count>0){
						map.put("result", true);
						
						UserInfo userInfo = userService.getUserById(id);
						request.getSession().setAttribute("user", userInfo);
					}else{
						map = Tools.resultMap(false, "新密码与旧密码相同");
					}
				}else{
					map = Tools.resultMap(false, "旧密码不正确");
				}
			}else{
				map = Tools.resultMap(false, "两次新密码不一致");
			}
		 }
		 return map;
    }
	
	/**
	 * 管理员页�?
	 * @return
	 */
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
		}
	    return new ModelAndView("userPage", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserInfo> list = userService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = userService.del(vo);
        return map;
    }
	
	@RequestMapping("/addPage")
    public ModelAndView addPage(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
        return new ModelAndView("addUser", model);
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = userService.add(vo);
        return map;
    }
}
