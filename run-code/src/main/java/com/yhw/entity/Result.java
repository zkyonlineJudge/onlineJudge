package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 运行结果类
 */
@Entity
@Table(name="result")
public class Result {
	
	public static Integer Waiting = 0;
	public static Integer ACCEPTED = 1;
	public static Integer Time_Limit_Exceeded = 2;
	public static Integer Memory_Limit_Exceeded = 3;
	public static Integer Wrong_Answer = 4;
	public static Integer Runtime_Error = 5;
	public static Integer Output_limit = 6;
	public static Integer Compile_Error = 7;
	public static Integer Presentation_Error = 8;
	public static Integer System_Error = 11;
	public static Integer Judging = 12;
	
	@Id  
    @GeneratedValue 
	private Integer id;
	@Column(name="code_id")
	private Integer codeId;
	/*
	 * 运行时间
	 */
	@Column
	private String time;
	/*
	 * 运行内存
	 */
	@Column
	private String size;
	/*
	 * --可以再建一个类
	 *  "Waiting": 0,
        "Accepted": 1,
        "Time Limit Exceeded": 2,
        "Memory Limit Exceeded": 3,
        "Wrong Answer": 4,
        "Runtime Error": 5,
        "Output limit": 6,
        "Compile Error": 7,
        "Presentation Error": 8,
        "System Error": 11,
        "Judging": 12,
	 */
	@Column
	private String result;
	/*
	 * 错误内容
	 */
	@Column
	private String error;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	
}
