package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Customer;
import cn.dyt.vo.IndexVo;

public interface CustomerService {

	List<Customer> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(Customer vo);

	Map<String, Object> edit(Customer vo);

}
