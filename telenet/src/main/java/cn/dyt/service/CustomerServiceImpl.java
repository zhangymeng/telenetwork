package cn.dyt.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.CustomerDao;
import cn.dyt.dao.CustomerTypeDao;
import cn.dyt.dao.OrderDao;
import cn.dyt.po.Customer;
import cn.dyt.po.CustomerType;
import cn.dyt.po.Order;
import cn.dyt.po.UserInfo;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerTypeDao customerTypeDao;
	
	@Override
	public List<Customer> findAll(IndexVo vo) {
		List<Customer> list = customerDao.findAll(vo);
		for(Customer s:list){
			if(s.getSex()==0){
				s.setSexStr("女");
			}else{
				s.setSexStr("男");
			}
			CustomerType ct = customerTypeDao.getById(s.getType());
			s.setTypeStr(ct.getName());
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		//查看订单，是否到期
		Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
		String cd = Tools.foarmatDateTime(createDate);
		vo.setTimeText("unix_timestamp('"+cd+"')<unix_timestamp(a.end_date)");
		vo.setcId(vo.getId());
		List<Order> list = orderDao.findAll(vo);
		if(list.size()>0){
			reason = "该客户有正在进行的订单，请勿删除";
		}else{
			customerDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(Customer vo) {
		boolean result = false;
		String reason = "";
		Customer customer = customerDao.getByPhone(vo.getPhone());
		if(customer!=null){
			reason = "手机号已存在";
		}else{
			Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
			vo.setCreateDate(createDate);
			customerDao.add(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> edit(Customer vo) {
		boolean result = false;
		String reason = "";
		Integer count = customerDao.edit(vo);
		if(count>0){
			result = true;
		}else{
			reason = "未修改";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> login(UserVo vo) {
		boolean result = false;
		String reason = "";
		Customer customer = customerDao.getByPhone(vo.getUsername());
		if(customer!=null){
			if(vo.getPassword().equals(customer.getPassword())){
				result = true;
				reason = customer.getId().toString();
			}else{
				reason = "密码不正确";
			}
		}else{
			reason = "用户不存在";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Customer getById(Integer id) {
		Customer cu = customerDao.getById(id);
		return cu;
	}

	@Override
	public Integer editUser(UserVo vo) {
		Integer count = customerDao.editUser(vo);
		return count;
	}

	@Override
	public Customer getByPhone(String phone) {
		Customer cu = customerDao.getByPhone(phone);
		if(cu!=null){
			if(cu.getSex()==1){
				cu.setSexStr("男");
			}else{
				cu.setSexStr("女");
			}
		}
		return cu;
	}

}
