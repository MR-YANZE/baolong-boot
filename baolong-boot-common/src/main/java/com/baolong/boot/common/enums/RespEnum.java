package com.baolong.boot.common.enums;

import lombok.Getter;

/**
 * 响应枚举类
 *
 * @author Baolong 2024年06月09 20:44
 * @version 1.0
 * @since 1.8
 */
@Getter
public enum RespEnum {
	SUCCESS(0, "成功"),
	FAILED(400, "失败"),
	UNKNOWN_ERROR(444, "未知错误"),
	SYSTEM_ERROR(500, "系统错误"),
	URL_PARAM_ERROR(4001, "URL参数异常"),
	FORM_PARAM_ERROR(4002, "form表单参数异常"),
	JSON_PARAM_ERROR(4003, "JSON参数异常"),
	RESP_MARK_ERROR(4004, "响应标识格式异常"),
	NOT_LOGIN(401, "未登录/token过期"),
	;
	private final int code;
	private final String msg;

	private RespEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
