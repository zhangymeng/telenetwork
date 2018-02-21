package cn.dyt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dyt.dao.OrderDao;
import cn.dyt.dao.PreferentialDao;
import cn.dyt.po.Order;
import cn.dyt.po.Preferential;
import cn.dyt.util.Tools;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.OrderVo;

@Service
public class PrefServiceImpl implements PrefService {

	@Autowired
	private PreferentialDao preferentialDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Preferential> findAll(IndexVo vo) {
		List<Preferential> list = preferentialDao.findAll(vo);
		for(Preferential p:list){
			if(p.getType()==1){
				p.setTypeStr("抵扣优惠");
			}else if(p.getType()==2){
				p.setTypeStr("折扣优惠");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Integer count = preferentialDao.del(vo);
		if(count>0){
			result = true;
		}
		return Tools.resultMap(result, reason);
	}
	
	public void addFun(OrderVo vo){
		preferentialDao.add(vo);
	}

	@Override
	public Map<String, Object> add(OrderVo vo) {
		boolean result = false;
		String reason = "";
		//判断金额、类型
		if(vo.getType()==1){
			//抵扣,优惠金额不大于条件金额
			if(vo.getConditions()>vo.getPref()){
				addFun(vo);
				result = true;
			}else{
				reason = "抵扣券优惠金额不能大于条件金额";
			}
		}else if(vo.getType()==2){
			//抵扣折扣在>1  <10之间
			if(1<=vo.getPref() && vo.getPref()<10){
				addFun(vo);
				result = true;
			}else{
				reason = "折扣为1`10之间";
			}
			
		}
		return Tools.resultMap(result, reason);
	}

	public void editFun(OrderVo vo){
		preferentialDao.edit(vo);
	}
	
	@Override
	public Map<String, Object> edit(OrderVo vo) {
		boolean result = false;
		String reason = "";
		//正在使用不能修改
		IndexVo indexVo = new IndexVo();
		indexVo.setPrefId(vo.getId());
		List<Order> list = orderDao.findAll(indexVo);
		if(list.size()>0){
			reason = "该优惠正在使用，请勿修改";
		}else{
			//判断金额、类型
			if(vo.getType()==1){
				//抵扣,优惠金额不大于条件金额
				if(vo.getConditions()>vo.getPref()){
					editFun(vo);
					result = true;
				}else{
					reason = "抵扣券优惠金额不能大于条件金额";
				}
			}else if(vo.getType()==2){
				//抵扣折扣在>1  <10之间
				if(1<=vo.getPref() && vo.getPref()<10){
					editFun(vo);
					result = true;
				}else{
					reason = "折扣为1`10之间";
				}
			}
		}
		return Tools.resultMap(result, reason);
	}

}
