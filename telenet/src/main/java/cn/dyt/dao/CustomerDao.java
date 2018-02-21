package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Customer;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

public interface CustomerDao {

	List<Customer> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Customer getByPhone(String phone);

	Integer add(Customer vo);

	Integer edit(Customer vo);

	Customer getById(Integer id);

	Integer editUser(UserVo vo);

}
