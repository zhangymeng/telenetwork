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
import cn.dyt.po.Order;
import cn.dyt.po.Preferential;
import cn.dyt.service.OrderService;
import cn.dyt.service.PrefService;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private PrefService prefService;
	
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
	    vo.setConditions(169);
	    List<Preferential> list = prefService.findAll(vo);
	    model.addAttribute("pList", list);
	    return new ModelAndView("orderPage", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Order> list = orderService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = orderService.del(vo);
        return map;
    }
	
	@RequestMapping("/getPreferential")
	@ResponseBody
	 public List<Preferential> getPreferential(HttpServletRequest request,IndexVo vo){
		vo.setConditions(vo.getNum()*vo.getMoney());
		List<Preferential> list = prefService.findAll(vo);
        return list;
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,OrderVo vo){
		if(vo.getPrefId()==null){
			vo.setPrefId(0);
		}
		Map<String,Object> map = orderService.add(vo);
        return map;
    }
	
	@RequestMapping("/cPage")
	public ModelAndView cPage(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
	    Customer user = (Customer)request.getSession().getAttribute("user");
	    if (user!=null){
	    	model.addAttribute("cId", user.getId());
	    }
	    return new ModelAndView("myOrder", model);
	}
}
