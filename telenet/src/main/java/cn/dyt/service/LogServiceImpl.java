package cn.dyt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.LogDao;
import cn.dyt.dao.ServerDao;
import cn.dyt.po.LogInfo;
import cn.dyt.po.Server;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;
	
	@Autowired
	private ServerDao serverDao;
	
	@Override
	public List<LogInfo> findAll(IndexVo vo) {
		List<LogInfo> list = logDao.findAll(vo);
		for(LogInfo l:list){
			l.setAdminStr(l.getUserInfo().getUsername());
			l.setName(l.getCustomer().getName());
			l.setPhone(l.getCustomer().getPhone());
			Server s = serverDao.getById(l.getsId());
			l.setServerStr(s.getNote());
			if(s.getType()==1){
				l.setTypeStr("意见反馈");
			}else{
				l.setTypeStr("报修");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		logDao.del(vo);
		return Tools.resultMap(true, "");
	}

}
