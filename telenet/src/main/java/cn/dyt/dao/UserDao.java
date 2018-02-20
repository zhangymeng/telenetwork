package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.UserInfo;
import cn.dyt.vo.IndexVo;
import cn.dyt.vo.UserVo;

public interface UserDao {

	UserInfo getUserByUsername(IndexVo vo);
	
	UserInfo getUserById(Integer id);

	Integer editUser(UserVo vo);

	List<UserInfo> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer edit(IndexVo vo);

	Integer add(IndexVo vo);

}
