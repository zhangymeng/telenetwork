package cn.dyt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.CustomerTypeDao;
import cn.dyt.po.CustomerType;
import cn.dyt.vo.IndexVo;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

	@Autowired
	private CustomerTypeDao customerTypeDao;
	
	@Override
	public List<CustomerType> findAll(IndexVo vo) {
		List<CustomerType> list = customerTypeDao.findAll(vo);
		return list;
	}

}
