package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.CustomerType;
import cn.dyt.vo.IndexVo;

public interface CustomerTypeDao {

	List<CustomerType> findAll(IndexVo vo);

	CustomerType getById(Integer id);

}
