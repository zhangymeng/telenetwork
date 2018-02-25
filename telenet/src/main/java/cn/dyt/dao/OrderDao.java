package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Order;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface OrderDao {

	List<Order> findAll(IndexVo vo);

	Order getById(Integer id);

	Integer del(IndexVo vo);

	Order getNew(Integer cId);

	Integer add(OrderVo vo);

}
