package cn.dyt.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.CustomerDao;
import cn.dyt.dao.OrderDao;
import cn.dyt.dao.PreferentialDao;
import cn.dyt.po.Customer;
import cn.dyt.po.Order;
import cn.dyt.po.Preferential;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private PreferentialDao preferentialDao;
	
	@Override
	public List<Order> findAll(IndexVo vo) {
		List<Order> list = orderDao.findAll(vo);
		for(Order o:list){
			o.setName(o.getCustomer().getName());
			if(o.getCustomer().getSex()==1){
				o.setSex("男");
			}else{
				o.setSex("女");
			}
			o.setPhone(o.getCustomer().getPhone());
			if(o.getPrefId()!=0){
				o.setTitle(o.getPreferential().getTitle());
				if(o.getPreferential().getType()==1){
					o.setType("抵扣");
				}else if(o.getPreferential().getType()==2){
					o.setType("折扣");
				}
				o.setPref(o.getPreferential().getPref());
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Order order = orderDao.getById(vo.getId());
		Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
		if(Timestamp.valueOf(order.getEndDate()+"-01 00:00:00").getTime()>createDate.getTime()){
			reason = "订单未结束，请勿删除";
		}else{
			orderDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	public void addOrder(OrderVo vo){
		vo.setMoney(vo.getMoney()*vo.getNum());
		//减去优惠券
		Preferential preferential = preferentialDao.getById(vo.getPrefId());
		if(preferential!=null){
			if(preferential.getType()==1){
				vo.setMoney(vo.getMoney()-preferential.getPref());
			}else{
				double mon = vo.getMoney()*((double)preferential.getPref()/10);
				int money = (int) mon;
				vo.setMoney(money);
			}
		}
		orderDao.add(vo);
	}
	
	@Override
	public Map<String, Object> add(OrderVo vo) {
		boolean result = false;
		String reason = "";
		Customer cu = customerDao.getByPhone(vo.getPhone());
		if(cu!=null){
			vo.setcId(cu.getId());
			//查询最后一条订单
			Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
			vo.setCreateDate(createDate);
			Order order = orderDao.getNew(cu.getId());
			if(order!=null){
				if(Timestamp.valueOf(order.getEndDate()+"-01 00:00:00").getTime()>createDate.getTime()){
					try {
						vo.setStartDate(Tools.mathTimetamp(Timestamp.valueOf(order.getEndDate()+"-01 00:00:00"), 1));
						vo.setEndDate(Tools.mathTimetamp(vo.getStartDate(),vo.getNum()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						vo.setStartDate(Tools.addAndSubtractDaysByCalendarTimetamp(1));
						vo.setEndDate(Tools.mathTimetamp(vo.getStartDate(),vo.getNum()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				try {
					vo.setStartDate(Tools.addAndSubtractDaysByCalendarTimetamp(1));
					vo.setEndDate(Tools.mathTimetamp(vo.getStartDate(),vo.getNum()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			addOrder(vo);
			result = true;
		}else{
			reason = "该客户不存在";
		}
		return Tools.resultMap(result, reason);
	}

}
