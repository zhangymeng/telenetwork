package cn.dyt.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.LogDao;
import cn.dyt.dao.ServerDao;
import cn.dyt.po.Server;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerDao serverDao;
	
	@Autowired
	private LogDao logDao;
	
	@Override
	public List<Server> findAll(IndexVo vo) {
		List<Server> list = serverDao.findAll(vo);
		for(Server s:list){
			s.setName(s.getCustomer().getName());
			s.setPhone(s.getCustomer().getPhone());
			if(s.getType()==1){
				s.setTypeStr("意见反馈");
			}else if(s.getType()==2){
				s.setTypeStr("报修");
			}
			if(s.getIsDel()==1){
				s.setStateStr("未处理");
			}else{
				s.setStateStr("已处理");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> del(OrderVo vo) {
		//删除server
		serverDao.del(vo);
		//添加log
		Server ser = serverDao.getById(vo.getId());
		vo.setsId(vo.getId());
		vo.setcId(ser.getcId());
		Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
		vo.setCreateDate(createDate);
		logDao.add(vo);
		return Tools.resultMap(true, "");
	}

	@Override
	public Map<String, Object> add(OrderVo vo) {
		Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
		vo.setCreateDate(createDate);
		serverDao.add(vo);
		return Tools.resultMap(true, "");
	}

}
