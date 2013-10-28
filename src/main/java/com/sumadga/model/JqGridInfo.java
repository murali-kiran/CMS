package com.sumadga.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.gson.Gson;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JqGridInfo<T> {
	
	/** Total number of pages */
	 private int total;
	 /** The current page number */
	 private int page;
	 /** Total number of records */
	 private int records;
	 /** The actual data */
	 private List<T> rows;
	 
	 public JqGridInfo(int total, int page, int records, List<T> rows) {
	  this.total = total;
	  this.page = page;
	  this.records = records;
	  this.rows = rows;
	 }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	 public String getJsonString(){
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("page", page);
		  map.put("total", total);
		  map.put("records", records);
		  map.put("rows", rows);
		  
		  return ((new Gson()).toJson(map));
	}
	 
}
