package com.yhw.model;

import com.yhw.entity.Question;

public class QuestionModel {
	//题目id
	private Integer id;
	private String rank;
	private String title;
	private Integer sucess;
	private Integer total;
	private String inputContent;
	private String outputContent;
	private Integer sizeLimit;
	private Integer amountTime;
	private String inputDesc;
	private String outputDesc;
	private Integer userId;
	private String description;
	public QuestionModel(Integer id,String rank,String title,Integer sucess,Integer total){
		this.id = id;
		this.rank = rank;
		this.title = title;
		this.sucess = sucess;
		this.total = total;
	}
	public QuestionModel(Question q) {
		this.id = q.getId();
		this.title = q.getTitle();
		this.description = q.getDescription();
		this.inputDesc = q.getInputDesc();
		this.outputDesc = q.getOutputDesc();
		this.userId = q.getUserId();
		this.amountTime = q.getAmountTime();
		this.sizeLimit = q.getSizeLimit();
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSucess() {
		return sucess;
	}

	public void setSucess(Integer sucess) {
		this.sucess = sucess;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getInputContent() {
		return inputContent;
	}

	public void setInputContent(String inputContent) {
		this.inputContent = inputContent;
	}

	public String getOutputContent() {
		return outputContent;
	}

	public void setOutputContent(String outputContent) {
		this.outputContent = outputContent;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
