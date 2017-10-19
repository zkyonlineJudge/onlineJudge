package com.yhw.model;

import com.yhw.entity.HighLadder;

public class HighLadderModel {
	private Integer id;
	private String username;
	private Double score;
	private String title;
	private String image;
	private Integer position;
	private String language;
	public HighLadderModel() {
		
	}
	public HighLadderModel(Integer id,String username,Double score,String title,String image,String language){
		this.id = id;
		this.username = username;
		this.score = score;
		this.title = title;
		this.image = image;
		this.language = language;
	}
	public HighLadderModel(HighLadder h, Integer position, String image , String title, String language) {
		this.position = position;
		this.score = h.getScore();
		this.title = title;
		this.image = image;
		this.language = language;
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
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
