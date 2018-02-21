package cn.dyt.vo;

import java.sql.Timestamp;

public class OrderVo {
	private Integer id;
	private String title;
	private Integer type;
	private Integer conditions;
	private Integer pref;
	
	private Integer money;
	private Integer prefId;
	private Integer cId;
	private Timestamp createDate;
	private Timestamp startDate;
	private Timestamp endDate;
	
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getPrefId() {
		return prefId;
	}
	public void setPrefId(Integer prefId) {
		this.prefId = prefId;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getConditions() {
		return conditions;
	}
	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}
	public Integer getPref() {
		return pref;
	}
	public void setPref(Integer pref) {
		this.pref = pref;
	}
	
}
