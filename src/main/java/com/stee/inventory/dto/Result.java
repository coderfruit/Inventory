package com.stee.inventory.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端的数据，统一封装在Result对象中
 * @author Jason
 * @param <T>
 *
 * @param <T>
 */

public class Result<T> implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6519371829110321653L;
	/**
	 * 返回标识符
	 */
	private String statusCode;
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 返回结果
	 */
	private List<Object> data;
	
	private List<T>  reportData;
	
	private List<T>  pageData;
	
	private Integer totalPage;
	
	private int pageSize;
	
	private int pageNumber;
	
	private int totaleItem;
	
	private String unit;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	
	public List<T> getReportData() {
		return reportData;
	}
	public void setReportData(List<T> reportData) {
		this.reportData = reportData;
	}
	
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotaleItem() {
		return totaleItem;
	}
	public void setTotaleItem(int totaleItem) {
		this.totaleItem = totaleItem;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setPageData(int pageNumber,int pageSize,int totaleItem,List<T> pageData){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totaleItem = totaleItem;
		Integer n = totaleItem%pageSize>0?1:0;
		this.totalPage = totaleItem/pageSize+n;
		this.pageData = pageData;
	}
	@Override
	public String toString() {
		return "Result [statusCode=" + statusCode + ", message=" + message + ", data=" + data + ", reportData="
				+ reportData + ", pageData=" + pageData + ", totalPage=" + totalPage + ", pageSize=" + pageSize
				+ ", pageNumber=" + pageNumber + ", totaleItem=" + totaleItem + ", unit=" + unit + "]";
	}
	

}
