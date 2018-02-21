package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Server;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface ServerDao {

	List<Server> findAll(IndexVo vo);

	Integer del(OrderVo vo);

	Integer add(OrderVo vo);

	Server getById(Integer id);

}
