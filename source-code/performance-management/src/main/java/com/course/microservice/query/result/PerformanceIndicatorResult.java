package com.course.microservice.query.result;

import java.util.List;

import com.course.microservice.entity.PerformanceIndicator;

public class PerformanceIndicatorResult {

	private List<PerformanceIndicator> items;

	private long total;

	private long totalPage;

	public PerformanceIndicatorResult(List<PerformanceIndicator> items, long total) {
		super();
		this.items = items;
		this.total = total;
	}

	public List<PerformanceIndicator> getItems() {
		return items;
	}

	public long getTotal() {
		return total;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setItems(List<PerformanceIndicator> items) {
		this.items = items;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

}
