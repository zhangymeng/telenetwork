package cn.dyt.vo;

import java.sql.Timestamp;

public class IndexVo {
	private Integer id;
	private String username;
	private Timestamp createDate;
	private String timeText;
	private Integer cId;
	
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
