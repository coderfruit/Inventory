package com.stee.inventory.dto;
/**
 * 请求参数配置
 * @author yuxiaolin
 *
 */
public class RequestObject {
	
	
	private String type;
	private String name;
	private Integer pageNo;
	private Integer pageSize;
	/****lamp Deatil***/
	private String lampId;
	private Integer showNum;
	private String startDate;
	private String endDate;
	private String Base64Info;
	private String exportType;
	private String exportTime;
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getLampId() {
		return lampId;
	}
	public void setLampId(String lampId) {
		this.lampId = lampId;
	}
	public Integer getShowNum() {
		return showNum;
	}
	public void setShowNum(Integer showNum) {
		if(showNum==null){
			showNum = 10;
		}
		this.showNum = showNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public String getBase64Info() {
		return Base64Info;
	}
	public void setBase64Info(String base64Info) {
		Base64Info = base64Info;
	}

	public String getExportTime() {
		return exportTime;
	}
	public void setExportTime(String exportTime) {
		this.exportTime = exportTime;
	}
}
