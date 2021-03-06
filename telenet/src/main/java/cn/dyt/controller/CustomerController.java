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

import cn.dyt.po.Customer;
import cn.dyt.po.CustomerType;
import cn.dyt.po.UserInfo;
import cn.dyt.service.CustomerService;
import cn.dyt.service.CustomerTypeService;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerTypeService customerTypeService;
	
	@RequestMapping("/page")
    public ModelAndView getListaUtentiView(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
			List<CustomerType> dList = customerTypeService.findAll(vo);
			model.addAttribute("dList", dList);
		}
        return new ModelAndView("customer", model);
    }
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Customer> list = customerService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }

	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = customerService.del(vo);
        return map;
    }
	
	@RequestMapping("/addPage")
    public ModelAndView addPage(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
		//�?��院系
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			List<CustomerType> dList = customerTypeService.findAll(vo);
			model.addAttribute("dList", dList);
		}
        return new ModelAndView("addCus", model);
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,Customer vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = customerService.edit(vo);
		}else{
			map = customerService.add(vo);
		}
        return map;
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
		 Customer user = (Customer)request.getSession().getAttribute("user");
		if(user!=null){
			if(vo.getPassword().equals(vo.getPasswords())){
				int id = user.getId();
				vo.setId(id);
				if(user.getPassword().equals(vo.getOldPassword())){
					Integer count = customerService.editUser(vo);
					if(count>0){
						map.put("result", true);
						
						Customer userInfo = customerService.getById(id);
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
	
	
	@RequestMapping("/typePage")
    public ModelAndView typePage(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
        return new ModelAndView("customerType", model);
    }
	
	@RequestMapping("/allType")
	@ResponseBody
    public Map<String,Object> allType(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<CustomerType> list = customerTypeService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/delType")
	@ResponseBody
    public Map<String,Object> delType(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = customerTypeService.del(vo);
        return map;
    }
	
	@RequestMapping("/addType")
	@ResponseBody
    public Map<String,Object> addType(HttpServletRequest request,Customer vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = customerTypeService.edit(vo);
		}else{
			map = customerTypeService.add(vo);
		}
        return map;
    }
	
	@RequestMapping("/getByPhone")
	@ResponseBody
    public Customer getByPhone(HttpServletRequest request,String phone){
		Customer cu = customerService.getByPhone(phone);
        return cu;
    }
}
