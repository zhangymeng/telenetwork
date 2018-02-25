package cn.dyt.vo;

import java.sql.Timestamp;

public class IndexVo {
	private Integer id;
	private String username;
	private String password;
	private Timestamp createDate;
	private String timeText;
	private Integer cId;
	private Integer type;
	private String name;
	private Integer prefId;
	private Integer isDel;
	private Integer num;
	private Integer money;
	private Integer conditions;

	public Integer getConditions() {
		return conditions;
	}

	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getPrefId() {
		return prefId;
	}

	public void setPrefId(Integer prefId) {
		this.prefId = prefId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getTimeText() {
		return timeText;
	}

	public void setTimeText(String timeText) {
		this.timeText = timeText;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
