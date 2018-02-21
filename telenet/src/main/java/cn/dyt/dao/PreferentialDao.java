package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Preferential;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface PreferentialDao {

	List<Preferential> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(OrderVo vo);

	Integer edit(OrderVo vo);

}
