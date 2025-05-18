package com.edu.vnua.fita.entity;

import java.time.LocalDate;

public class DataCrawl {
	private LocalDate startDate;
	private String html;
	
	public DataCrawl() {
		
	}
	
	public DataCrawl(LocalDate startDate, String html) {
		this.startDate = startDate;
		this.html = html;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	
}
