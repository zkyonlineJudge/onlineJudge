package com.yhw.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 题库
 */
@Entity
@Table(name="question")
public class Question {
	@Id  
    @GeneratedValue 
	private Integer id;
	/*
	 * 题目描述
	 */
	@Column(name="dsc")
	private String description;
	/*
	 * 题目难度 ABCDE
	 */
	@Column
	private String rank;
	/*
	 * 标题
	 */
	@Column
	private String title;
	
	/*
	 * 时间限制 单位ms
	 */
	@Column(name="time_limit")
	private Integer timeLimit;
	/*
	 * 内存限制 单位kb
	 */
	@Column(name="size_limit")
	private Integer sizeLimit;
	/*
	 * 做题总耗时限制min
	 */
	@Column(name="amount_time")
	private Integer amountTime;
	/*
	 * 输入描述
	 */
	@Column(name="input_desc")
	private String inputDesc;
	
	/*
	 * 输出描述
	 */
	@Column(name="output_desc")
	private String outputDesc;
	
	/*
	 * 输入样例集合
	 * 暂时用txt文本存储
	 * 多个样例之间建议用一个特殊的分割符号
	 * window文本换行会加上\r\n
	 * linux文本换行会加上\n
	 * 记得处理
	 */
	@Column(name="input_path")
	private String inputPath;
	
	/*
	 * 输出样例集合
	 * 暂时用txt文本存储
	 */
	@Column(name="output_path")
	private String outputPath;
	
	/*
	 * 题目来源  如网络
	 */
	@Column
	private String source;
	/*
	 * 题目上传者
	 */
	@Column(name = "user_id")
	private Integer userId;
	/*
	 * 题目做题总次数
	 * 一开始一定要初始化 0
	 */
	@Column
	private Integer total;
	/*
	 * 题目成功运行次数
	 * 一开始一定要初始化 0
	 */
	@Column
	private Integer sucess;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSucess() {
		return sucess;
	}
	public void setSucess(Integer sucess) {
		this.sucess = sucess;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Integer getSizeLimit() {
		return sizeLimit;
	}
	public void setSizeLimit(Integer sizeLimit) {
		this.sizeLimit = sizeLimit;
	}
	public Integer getAmountTime() {
		return amountTime;
	}
	public void setAmountTime(Integer amountTime) {
		this.amountTime = amountTime;
	}
	public String getInputDesc() {
		return inputDesc;
	}
	public void setInputDesc(String inputDesc) {
		this.inputDesc = inputDesc;
	}
	public String getOutputDesc() {
		return outputDesc;
	}
	public void setOutputDesc(String outputDesc) {
		this.outputDesc = outputDesc;
	}
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
