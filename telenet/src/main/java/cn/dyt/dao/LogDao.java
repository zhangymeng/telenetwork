package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.LogInfo;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface LogDao {

	Integer add(OrderVo vo);

	List<LogInfo> findAll(IndexVo vo);

	Integer del(IndexVo vo);

}
