package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Customer;
import cn.dyt.vo.IndexVo;

public interface CustomerDao {

	List<Customer> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Customer getByPhone(String phone);

	Integer add(Customer vo);

	Integer edit(Customer vo);

}
