package com.yez.wiki.entity;

public class ResponseMessage {
	public String code;
	public String msg;
	public Object data;
	
	public static final String SUCCESS = "200";
	public static final String FAIL = "400";
	public static final String ACCESS_DENIED = "403";
	
	//构造器
	public ResponseMessage() {}
	public ResponseMessage(String code) {
		this.code = code;
	}
	public ResponseMessage(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public ResponseMessage(String code, Object data) {
		this.code = code;
		this.data = data;
	}
	public ResponseMessage(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	//创建成功响应信息
	public static ResponseMessage success() {
		return new ResponseMessage(SUCCESS);
	}
	public static ResponseMessage success(String msg) {
		return new ResponseMessage(SUCCESS, msg);
	}
	public static ResponseMessage success(Object data) {
		return new ResponseMessage(SUCCESS, data);
	}
	public static ResponseMessage success(String msg, Object data) {
		return new ResponseMessage(SUCCESS, msg, data);
	}
	
	//创建失败响应信息
	public static ResponseMessage fail() {
		return new ResponseMessage(FAIL);
	}
	public static ResponseMessage fail(String message) {
		return new ResponseMessage(FAIL, message);
	}
	
	//创建拒绝访问响应信息
	public static ResponseMessage warning() {
		return new ResponseMessage(ACCESS_DENIED);
	}
	public static ResponseMessage warning(String message) {
		return new ResponseMessage(ACCESS_DENIED, message);
	}
	
	//Getter And Setter
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
