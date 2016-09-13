package com.hcq.web.model;

public class HotWord {
	private String word;
	private Long total;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "HotWord [word=" + word + ", total=" + total + "]";
	}
	public HotWord() {
		super();
	}
	public HotWord(String word, Long total) {
		super();
		this.word = word;
		this.total = total;
	}
}
