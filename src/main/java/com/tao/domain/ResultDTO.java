package com.tao.domain;

import java.io.Serializable;

public class ResultDTO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ERROR_HTTP_STATUS = 500;

	private boolean ok;
	private String code;
	private String msg;
	private T result;

	public ResultDTO() {
	}

	// 暂时增加无code构造方法，后续优化code
	public ResultDTO(boolean ok, String msg) {
		this(ok, null, msg, null);
	}

	// 暂时增加无code构造方法，后续优化code
	public ResultDTO(boolean ok, String msg, T value) {
		this(ok, null, msg, value);
	}

	public ResultDTO(boolean ok, String code, String msg) {
		this(ok, code, msg, null);
	}

	public ResultDTO(boolean ok, String code, String msg, T value) {
		this.ok = ok;
		this.code = code;
		this.msg = msg;
		this.result = value;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResultDTO [ok=" + ok + ", code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}

	// 暂时增加无code ok方法，后续优化code
	public static <T> ResultDTO<T> ok(T ret) {
		return new ResultDTO<T>(true, "", null, ret);
	}

	// 暂时增加无code ok方法，后续优化code
	public static <T> ResultDTO<T> ok(String msg, T ret) {
		return new ResultDTO<T>(true, "", msg, ret);
	}

	public static <T> ResultDTO<T> ok(String code, String msg, T ret) {
		return new ResultDTO<T>(true, code, msg, ret);
	}

	public static <T> ResultDTO<T> error(String code, String msg) {
		return new ResultDTO<T>(false, code, msg, null);
	}

}
