package cn.dyt.service;

import java.util.List;
import java.util.Map;

import cn.dyt.po.LogInfo;
import cn.dyt.vo.IndexVo;

public interface LogService {

	List<LogInfo> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

}
