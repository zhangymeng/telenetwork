package cn.dyt.dao;

import java.util.List;

import cn.dyt.po.Menu;

public interface MenuDao {

	List<Menu> findAll(List<Integer> list);

}
