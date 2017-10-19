package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 级别类
 * 根据天梯做的题目数量和对应的难度改变对应的级别rank属性
 */
@Entity
@Table(name="rank")
public class Rank {
	@Id  
    @GeneratedValue 
	private Integer id;
	
	
	/*
	 * 级别有ABCDE，最高的级别为A
	 */
	@Column
	private String rank;
	
	/*
	 * 对应级别的徽章
	 */
	@Column
	private String image;
	
	/*
	 * 这里定义对应难度的题目数量
	 * 例如用户在C级别，要AC过minLimit数量的题目才能成为对应难度的rank
	 */
	@Column(name="min_limit")
	private Integer minLimit;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(Integer minLimit) {
		this.minLimit = minLimit;
	}

	
	
	
}
