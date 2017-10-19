package com.yhw.mq;

import com.yhw.core.Runner;
import com.yhw.model.QuestionDTO;

public class RunCodeThread implements Runnable {
	
	private QuestionDTO dto;
	
	public RunCodeThread(QuestionDTO dto) {
		this.dto = dto;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		Runner runner = new Runner();
		runner.compileAndRun(dto);
	}

}
