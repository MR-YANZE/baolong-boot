package com.baolong.boot.common.exception;

/**
 * 业务异常类
 *
 * @author Baolong 2024/6/6 19:50
 * @version 1.0
 * @since 1.8
 */
public class BizException extends RuntimeException {

	private int code;

	public BizException(String msg) {
		super(msg);
	}

	public BizException(int code, String msg) {
		super(msg);
	}

	public BizException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BizException(int code, String msg, Throwable cause) {
		super(msg, cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

