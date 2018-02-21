package cn.dyt.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.dyt.po.Customer;
import cn.dyt.po.Menu;
import cn.dyt.po.UserInfo;
import cn.dyt.service.CustomerService;
import cn.dyt.service.UserService;
import cn.dyt.vo.UserVo;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 登录页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginPage")
    public ModelAndView loginPage(UserVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
        return new ModelAndView("login", model);
    }
	
	/**
	 * 登录 username password
	 * @param vo
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
    public Map<String,Object> login(UserVo vo,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(vo.getUsername());
        if( !isNum.matches() ){
        	//不是全数字，管理员
	   		 map = userService.login(vo);
			 
	   		 boolean result = (Boolean) map.get("result");
	   		 String idStr = (String) map.get("reason");
	   		 
	   		 if(result){
	   			 Integer id = Integer.parseInt(idStr);
	   			 UserInfo user = userService.getUserById(id);
	   			 request.getSession().setAttribute("user", user);
	   		 }
        }else{
        	map = customerService.login(vo);
        	
	   		boolean result = (Boolean) map.get("result");
	   		String idStr = (String) map.get("reason");
	   		if(result){
	   			Integer id = Integer.parseInt(idStr);
	   			Customer user = customerService.getById(id);
	   			request.getSession().setAttribute("user", user);
	   		}
        }

		 
		 return map;
    }
	
	/**
	 * 登录后主页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
		String urlStr = "login";
		ModelMap model = new ModelMap();
		UserInfo user = null;
		Customer users = null;
		try{
			user = (UserInfo)request.getSession().getAttribute("user");
		}catch (Exception e) {
			users = (Customer)request.getSession().getAttribute("user");
		}
		if(user!=null){
			urlStr = "index";
			model.addAttribute("userInfo", user);
			//菜单
			List<Menu> menuList = userService.getMenu(user.getRoleId());
			model.addAttribute("menuList", menuList);
		}else{
			if(users!=null){
				urlStr = "index";
				model.addAttribute("userInfo", users);
				//菜单
				List<Menu> menuList = userService.getMenu(users.getRoleId());
				model.addAttribute("menuList", menuList);
			}
		}
        
        return new ModelAndView(urlStr, model);
    }
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
		ModelMap model = new ModelMap();
		Enumeration<?> em = request.getSession().getAttributeNames();
		  while(em.hasMoreElements()){
		   request.getSession().removeAttribute(em.nextElement().toString());
		}
        return new ModelAndView("login", model);
    }
	
	
	@RequestMapping("/basic")
    public ModelAndView basic(HttpServletRequest request){
		ModelMap model = new ModelMap();
        return new ModelAndView("basic", model);
    }
}
