package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 	 对于天梯排名生成对应的称号
 */
@Entity
@Table(name="high_ladder_title")
public class HighLadderTitle {
	@Id  
    @GeneratedValue 
	private Integer id;
	
	/*
	 * 称号
	 */
	@Column
	private String title;
	/*
	 * 对应称号的最小限制
	 * 如minLimit为50 title为最强王者
	 * 如果排名为25，则title为最强王者
	 * 
	 */
	@Column(name="min_limit")
	private Integer minLimit;
	
	/*
	 * 对应称号的徽章
	 */
	@Column
	private String image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(Integer minLimit) {
		this.minLimit = minLimit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
