package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Order;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface OrderService {

	List<Order> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(OrderVo vo);

}
