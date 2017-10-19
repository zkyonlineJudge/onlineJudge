package com.yhw.model;

import com.yhw.entity.Code;
import com.yhw.entity.Question;

public class QuestionDTO {
	private String content;
	/**
	 * 问题id
	 */
	private Integer id;
	/**
	 * 代码id
	 */
	private Integer codeId;
	private String language;
	
	private Long timeLimit;
	
	private Long sizeLimit;
	
	public QuestionDTO() {
		
	}
	
	public QuestionDTO(Code c, String language, Question q) {
		this.codeId = c.getId();
		this.content = c.getContent();
		this.id = c.getQid();
		this.language = language;
		this.sizeLimit = new Long(q.getSizeLimit());
		this.timeLimit = new Long(q.getTimeLimit());
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Long timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Long getSizeLimit() {
		return sizeLimit;
	}

	public void setSizeLimit(Long sizeLimit) {
		this.sizeLimit = sizeLimit;
	}

	@Override
	public String toString() {
		return "QuestionDTO [content=" + content + ", id=" + id + ", codeId=" + codeId + ", language=" + language
				+ ", timeLimit=" + timeLimit + ", sizeLimit=" + sizeLimit + "]";
	}
	
	
}
