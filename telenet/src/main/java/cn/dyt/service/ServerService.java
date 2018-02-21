package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Server;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface ServerService {

	List<Server> findAll(IndexVo vo);

	Map<String, Object> del(OrderVo vo);

	Map<String, Object> add(OrderVo vo);

}
