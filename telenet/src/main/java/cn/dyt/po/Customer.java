package cn.dyt.po;

import java.sql.Timestamp;

import cn.dyt.util.Tools;

public class Customer {
	private Integer id;
	private String name;
	private Integer sex;
	private Integer isDel;
	private String phone;
	private String createDate;
	private String birthday;
	private Integer type;
	private String sexStr;
	private String typeStr;
	
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getSexStr() {
		return sexStr;
	}
	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIs_del(Integer isDel) {
		this.isDel = isDel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = Tools.foarmatDateTime(createDate);
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = Tools.foarmatDate(birthday);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
