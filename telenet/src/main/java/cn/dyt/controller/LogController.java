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

import cn.dyt.po.LogInfo;
import cn.dyt.po.UserInfo;
import cn.dyt.service.LogService;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request){
	    ModelMap model = new ModelMap();
	    return new ModelAndView("logPage", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<LogInfo> list = logService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = logService.del(vo);
        return map;
    }
}
