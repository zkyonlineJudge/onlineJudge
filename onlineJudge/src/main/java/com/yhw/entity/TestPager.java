package com.yhw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 试卷类  不限时  就不算分数了，显示答对的个数
 */
@Entity
@Table(name="test_pager")
public class TestPager {
	@Id  
    @GeneratedValue 
	private Integer id;
	@Column(name="begin_time")
	private Date beginTime;
	/*
	 * 总题数
	 */
	@Column
	private Integer count;
	
	/**
	 * 整张试卷的时间限制
	 */
	@Column(name="time_limit")
	private Integer timeLimit;
	@Column(name="user_id")
	private Integer userId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
