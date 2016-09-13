package com.hcq.web.model;

import java.io.Serializable;

public class RobotWord implements Serializable{
	private static final long serialVersionUID = -7234222888774176249L;
	private String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "RobotWord [word=" + word + "]";
	}

	public RobotWord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RobotWord(String word) {
		super();
		this.word = word;
	}
	
	
	
}
