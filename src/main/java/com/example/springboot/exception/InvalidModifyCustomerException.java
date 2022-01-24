package com.example.springboot.exception;

import com.example.springboot.model.ReturnCode;

public class InvalidModifyCustomerException extends RuntimeException{
	
	private ReturnCode errorCode;//CUSTOMER_EXIST
	protected String errorMsg; //客戶帳號已存在
	
	public InvalidModifyCustomerException(String errorMsg) {
		setErrorCode(getCode()).setErrorMsg(getMsg()).appendErrorMsg(errorMsg)  ;
	}
	
	
	public ReturnCode getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	public InvalidModifyCustomerException setErrorCode(ReturnCode errorCode) {
		this.errorCode = errorCode;
		return this;
		
	}
	public InvalidModifyCustomerException setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		return this ;
	}
	
	
	public InvalidModifyCustomerException appendErrorMsg(String message) {
		this.errorMsg = errorMsg+message;
		return this;
	}
	
	protected  ReturnCode getCode() {
		return ReturnCode.CUSTOMER_EXIST;
	} 
	protected  String getMsg() {
		return "客戶帳號已存在";
	}  

}
