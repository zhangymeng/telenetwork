package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Customer;
import cn.dyt.po.CustomerType;
import cn.dyt.vo.IndexVo;

public interface CustomerTypeDao {

	List<CustomerType> findAll(IndexVo vo);

	CustomerType getById(Integer id);

	Integer del(IndexVo vo);

	Integer add(Customer vo);

	Integer edit(Customer vo);

}
