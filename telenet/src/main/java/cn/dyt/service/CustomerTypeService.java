package cn.dyt.service;

import java.util.List;

import cn.dyt.po.CustomerType;
import cn.dyt.vo.IndexVo;

public interface CustomerTypeService {

	List<CustomerType> findAll(IndexVo vo);

}
