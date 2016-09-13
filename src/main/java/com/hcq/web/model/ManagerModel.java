package com.hcq.web.model;

public class ManagerModel {
	private Integer limit;
	private Integer offset;
	private Integer total; 
	private String order;
	
	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}


	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public ManagerModel() {
		super();
	}

	public ManagerModel(Integer limit, Integer offset, Integer total, String order) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.total = total;
		this.order = order;
	}

}
