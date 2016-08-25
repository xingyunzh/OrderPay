package com.xingyunzh.orderpay.dto;

import java.io.Serializable;

public class ResponseWrapper implements Serializable {
	private static final long serialVersionUID = -3217328866302976615L;
	
	private int code = 0; // 0 - success
	private Object body;

	public ResponseWrapper(int code, Object body) {
		this.code = code;
		this.body = body;
	}
	
	public ResponseWrapper(Object body) {
		super();
		this.body = body;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}
