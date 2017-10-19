package com.yhw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 代码类
 */
@Entity
@Table(name="code")
public class Code {
	
	public static String TYPE_LADDER = "hl";
	public static String TYPE_EXERCISE = "ex";
	public static String TYPE_TEST_PAGER = "tp";
	
	@Id  
    @GeneratedValue 
	private Integer id;
	/*
	 * 代码作者
	 */
	@Column(name="user_id")
	private Integer userId;
	@Column(name="q_id")
	private Integer qid;
	/*
	 * 代码内容
	 */
	@Column(name="content")
	private String content;
	/*
	 * 代码语言
	 */
	@Column(name="language_id")
	private Integer languageId;
	
	/*
	 * 代码生成日期
	 */
	@Column(name="date")
	private Date date;
	
	@Column(name="result_id")
	private Integer resultId;
	/*
	 * 答题类型 天梯  hl  练习 ex  试卷 tp
	 */
	@Column(name="type")
	private String type;
	/*
	 * 天梯开始答题时间  专属于天梯模式
	 */
	@Column(name="begin_time")
	private Date beginTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
}
