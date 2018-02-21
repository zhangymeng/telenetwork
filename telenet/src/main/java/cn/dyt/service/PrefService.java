package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.Preferential;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

public interface PrefService {

	List<Preferential> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(OrderVo vo);

	Map<String, Object> edit(OrderVo vo);

}
