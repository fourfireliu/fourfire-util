package com.fourfire.entity.result;

/**
 * 提供基础的返回值属性
 * 
 * @author liuyi
 * @date 2016-07-15
 */
public class ResultSupport {
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	private String errorMsg;
	private long errorCode;
	private boolean isSuccess;
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		return "ResultSupport [errorMsg=" + errorMsg + ", errorCode="
				+ errorCode + ", isSuccess=" + isSuccess + "]";
	}	
}
