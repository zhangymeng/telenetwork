package cn.dyt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.CustomerDao;
import cn.dyt.dao.CustomerTypeDao;
import cn.dyt.po.Customer;
import cn.dyt.po.CustomerType;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

	@Autowired
	private CustomerTypeDao customerTypeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<CustomerType> findAll(IndexVo vo) {
		List<CustomerType> list = customerTypeDao.findAll(vo);
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		//是否分配
		vo.setType(vo.getId());
		List<Customer> list = customerDao.findAll(vo);
		if(list.size()>0){
			reason = "该客户类型已被分配，请勿删除";
		}else{
			customerTypeDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> edit(Customer vo) {
		boolean result = false;
		String reason = "";
		IndexVo inVo = new IndexVo();
		inVo.setName(vo.getName());
		List<CustomerType> list = customerTypeDao.findAll(inVo);
		if(list.size()<1 || (list.size()>0 && list.get(0).getId()==vo.getId())){
			customerTypeDao.edit(vo);
			result = true;
		}else{
			reason = "该客户类型已存在";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(Customer vo) {
		boolean result = false;
		String reason = "";
		IndexVo inVo = new IndexVo();
		inVo.setName(vo.getName());
		List<CustomerType> list = customerTypeDao.findAll(inVo);
		if(list.size()>0){
			reason = "该客户类型已存在";
		}else{
			customerTypeDao.add(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

}
