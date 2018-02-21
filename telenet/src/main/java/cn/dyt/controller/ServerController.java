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
import cn.dyt.po.Server;
import cn.dyt.po.UserInfo;
import cn.dyt.service.ServerService;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

@Controller
@RequestMapping("/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
	    model.addAttribute("type", vo.getType());
	    return new ModelAndView("server", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		vo.setIsDel(1);
		List<Server> list = serverService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,OrderVo vo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			vo.setAdminId(user.getId());
		}
		Map<String,Object> map = serverService.del(vo);
        return map;
    }
	
	@RequestMapping("/cPage")
	public ModelAndView cPage(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
	    return new ModelAndView("cServer", model);
	}
	
	@RequestMapping("/cfindAll")
	@ResponseBody
    public Map<String,Object> cfindAll(HttpServletRequest request,IndexVo vo){
		Customer user = (Customer)request.getSession().getAttribute("user");
		if(user!=null){
			vo.setcId(user.getId());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Server> list = serverService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,OrderVo vo){
		Customer user = (Customer)request.getSession().getAttribute("user");
		if(user!=null){
			vo.setcId(user.getId());
		}
		Map<String,Object> map = serverService.add(vo);
        return map;
    }
}
