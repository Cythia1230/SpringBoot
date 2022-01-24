package com.example.springboot.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RaceInfoDTO {
	
	
	private Integer	raceId ; 
	private String	raceName ;
	private LocalDateTime  holdDate;
	private String	holdPalace;
	private String	holdPalaceAdd;
	private LocalDateTime  enrollStDate;
	private LocalDateTime  enrollEndDate;
	private String	sponsor;
	private Integer	minimumFee;
	private Integer	maxGroup;
	private Integer	ExtraCharge;
	private String	remark;
	
	//時間轉文字格式
	private String stringHoldDate;
	private String stringEnrollStDate; 
	private String stringEnrollEndDate; 
	
	public String getStringHoldDate() {
		return stringHoldDate;
	}
	public void setStringHoldDate(String stringHoldDate) {
		this.stringHoldDate = stringHoldDate;
	}
	public String getStringEnrollStDate() {
		return stringEnrollStDate;
	}
	public void setStringEnrollStDate(String stringEnrollStDate) {
		this.stringEnrollStDate = stringEnrollStDate;
	}
	public String getStringEnrollEndDate() {
		return stringEnrollEndDate;
	}
	public void setStringEnrollEndDate(String stringEnrollEndDate) {
		this.stringEnrollEndDate = stringEnrollEndDate;
	}

	
	
	
	public Integer getRaceId() {
		return raceId;
	}
	public void setRaceId(Integer raceId) {
		this.raceId = raceId;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	public LocalDateTime  getHoldDate() {
		return holdDate;
	}
	
	/*
	public String getStringHoldDate(LocalDateTime  holdDate) {
		String stringholdDate =timeparser(holdDate);
		return stringholdDate;
	}
	
	public String getStringEnrollStDate(LocalDateTime  enrollStDate) {
		String stringEnrollStDate =timeparser(enrollStDate);
		return stringEnrollStDate;
	}
	
	public String getStringEnrollEndDate(LocalDateTime  enrollEndDate) {
		String stringEnrollEndDate =timeparser(holdDate);
		return stringEnrollEndDate;
	}
	*/
	
	public void setHoldDate(LocalDateTime  holdDate) {
		this.holdDate = holdDate;
	}
	public String getHoldPalace() {
		return holdPalace;
	}
	public void setHoldPalace(String holdPalace) {
		this.holdPalace = holdPalace;
	}
	public String getHoldPalaceAdd() {
		return holdPalaceAdd;
	}
	public void setHoldPalaceAdd(String holdPalaceAdd) {
		this.holdPalaceAdd = holdPalaceAdd;
	}
	public LocalDateTime  getEnrollStDate() {
		return enrollStDate;
	}
	public void setEnrollStDate(LocalDateTime  enrollStDate) {
		this.enrollStDate = enrollStDate;
	}
	public LocalDateTime  getEnrollEndDate() {
		return enrollEndDate;
	}
	public void setEnrollEndDate(LocalDateTime  enrollEndDate) {
		this.enrollEndDate = enrollEndDate;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public Integer getMinimumFee() {
		return minimumFee;
	}
	public void setMinimumFee(Integer minimumFee) {
		this.minimumFee = minimumFee;
	}
	public Integer getMaxGroup() {
		return maxGroup;
	}
	public void setMaxGroup(Integer maxGroup) {
		this.maxGroup = maxGroup;
	}
	public Integer getExtraCharge() {
		return ExtraCharge;
	}
	public void setExtraCharge(Integer extraCharge) {
		ExtraCharge = extraCharge;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/*	
	public String timeparser(LocalDateTime localDateTime) {
      
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = localDateTime.format(formatter); 
		
		return  formatDateTime;
	}
	*/
	/*
		public  setStringHoldDate(LocalDateTime  holdDate) {
		
		this.holdDate = holdDate;;
	}
	
	public  setStringEnrollStDate(LocalDateTime  enrollStDate) {
		String stringEnrollStDate =timeparser(enrollStDate);
		return stringEnrollStDate;
	}
	
	public  setStringEnrollEndDate(LocalDateTime  enrollEndDate) {
		String stringEnrollEndDate =timeparser(holdDate);
		return stringEnrollEndDate;
	}
	 */
	
	
	
}
