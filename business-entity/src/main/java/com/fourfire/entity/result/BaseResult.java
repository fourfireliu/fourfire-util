package com.fourfire.entity.result;

import java.io.Serializable;

/**
 * 基本的返回类型, 返回内容为单个对象
 * 
 * @author liuyi
 *
 * @param <T>
 * @date 2016-07-15
 */
public class BaseResult<T> extends ResultSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -434173357115447940L;

	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BaseResult [value=" + value + ", isSuccess()=" + isSuccess()
				+ ", getErrorMsg()=" + getErrorMsg() + ", getErrorCode()="
				+ getErrorCode() + "]";
	}
}
