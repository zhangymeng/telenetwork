package cn.dyt.po;

import java.sql.Timestamp;

import cn.dyt.util.Tools;

public class Order {
	private Integer id;
	private Integer money;
	private Integer prefId;
	private Integer cId;
	private String createDate;
	private String startDate;
	private String endDate;
	
	private String name;
	private String sex;
	private String phone;
	private String title="/";
	private String type="/";
	private Integer pref=0;
	
	private Customer customer;
	private Preferential preferential;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Preferential getPreferential() {
		return preferential;
	}
	public void setPreferential(Preferential preferential) {
		this.preferential = preferential;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPref() {
		return pref;
	}
	public void setPref(Integer pref) {
		this.pref = pref;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = Tools.foarmatDateTime(createDate);
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = Tools.foarmatMonthDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = Tools.foarmatMonthDate(endDate);
	}
	
}
