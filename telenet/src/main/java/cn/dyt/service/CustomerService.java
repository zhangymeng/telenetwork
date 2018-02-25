package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Customer;
import cn.dyt.po.UserInfo;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

public interface CustomerService {

	List<Customer> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(Customer vo);

	Map<String, Object> edit(Customer vo);

	Map<String, Object> login(UserVo vo);

	Customer getById(Integer id);

	Integer editUser(UserVo vo);

	Customer getByPhone(String phone);

}
