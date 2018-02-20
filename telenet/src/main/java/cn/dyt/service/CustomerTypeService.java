package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Customer;
import cn.dyt.po.CustomerType;
import cn.dyt.vo.IndexVo;

public interface CustomerTypeService {

	List<CustomerType> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> edit(Customer vo);

	Map<String, Object> add(Customer vo);

}
