package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Order;
import cn.dyt.vo.IndexVo;

public interface OrderDao {

	List<Order> findAll(IndexVo vo);

}
