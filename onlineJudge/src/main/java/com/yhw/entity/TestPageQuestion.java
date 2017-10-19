package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 试卷类和问题类的中间表
 */
@Entity
@Table(name="test_pager_question")
public class TestPageQuestion {
	@Id  
    @GeneratedValue 
	private Integer id;
	/*
	 * 关联testpager对象
	 */
	@Column(name="tpid")
	private Integer tpid;
	/*
	 * 关联question对象
	 */
	@Column(name="qid")
	private Integer qid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTpid() {
		return tpid;
	}
	public void setTpid(Integer tpid) {
		this.tpid = tpid;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
}
