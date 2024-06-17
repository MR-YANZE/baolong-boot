package com.baolong.boot.common.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一响应类
 * <p>
 * 响应码：0-成功；400-失败
 * <p>
 * 响应标识：SUCCESS-成功；FAILED-失败
 *
 * @author Baolong 2024/6/6 0:00
 * @version 1.0
 * @since 1.8
 */
@Schema(name = "Result<T>", description = "统一响应实体")
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
	private Result() {
	}

	public static final int SUCCESS_CODE = 0;
	public static final int FAILED_CODE = 400;
	public static final boolean TURE = true;
	public static final boolean FALSE = false;
	public static final String SUCCESS_MSG = "成功";
	public static final String FAILED_MSG = "失败";
	public static final String SUCCESS_MARK = "SUCCESS";
	public static final String FAILED_MARK = "FAILED";
	/**
	 * 响应码
	 */
	@Schema(description = "响应码", example = "0-成功, 400-普通业务异常")
	private int code;
	/**
	 * 成功标识
	 */
	@Schema(description = "成功标识", example = "true-成功, false-失败")
	private boolean success;
	/**
	 * 响应信息
	 */
	@Schema(description = "响应信息")
	private String msg;
	/**
	 * 响应数据
	 */
	@Schema(description = "响应数据")
	private T data;
	/**
	 * 响应标识
	 */
	@Schema(description = "响应标识")
	private String mark;

	/**
	 * 判断“有”
	 *
	 * @param s 字符串
	 * @return boolean
	 */
	private static boolean judgeHas(String s) {
		return s != null && !s.isEmpty();
	}

	/**
	 * 创建当前对象
	 *
	 * @param code    响应码
	 * @param success 成功标识
	 * @param msg     响应信息
	 * @param data    响应数据
	 * @param mark    响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>     T
	 * @return Result<T>
	 */
	private static <T> Result<T> create(int code, boolean success, String msg, T data, String mark) {
		if (judgeHas(mark)) {
			if (!mark.matches("^[A-Z_]+$")) {
				throw new RIllegalArgumentException("响应标识参数格式异常[必须为大写字母、下划线组成]");
			}
		}
		return new Result<T>().setCode(code).setSuccess(success).setMsg(msg).setData(data).setMark(mark);
	}

	/**
	 * 自定义响应格式
	 *
	 * @param code    响应码
	 * @param success 成功标识
	 * @param msg     响应信息
	 * @param data    响应数据
	 * @param <T>     T
	 * @return Result<T>
	 */
	public static <T> Result<T> custom(int code, boolean success, String msg, T data) {
		return custom(code, success, msg, data, false, null);
	}

	/**
	 * 自定义响应格式
	 *
	 * @param code     响应码
	 * @param success  成功标识
	 * @param msg      响应信息
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> custom(int code, boolean success, String msg, T data, boolean openMark, String mark) {
		return openMark ? create(code, success, msg, data, mark) : create(code, success, msg, data, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param <T> T
	 * @return Result<T>
	 */
	public static <T> Result<T> success() {
		return success(false);
	}

	/**
	 * 成功响应格式
	 *
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(boolean openMark) {
		return success(openMark, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(SUCCESS_CODE, TURE, SUCCESS_MSG, null, mark)
						: create(SUCCESS_CODE, TURE, SUCCESS_MSG, null, SUCCESS_MARK)
		) : create(SUCCESS_CODE, TURE, SUCCESS_MSG, null, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param msg 响应信息
	 * @param <T> T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(String msg) {
		return success(msg, false);
	}

	/**
	 * 成功响应格式
	 *
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(String msg, boolean openMark) {
		return success(msg, openMark, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(String msg, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(SUCCESS_CODE, TURE, msg, null, mark)
						: create(SUCCESS_CODE, TURE, msg, null, SUCCESS_MARK)
		) : create(SUCCESS_CODE, TURE, msg, null, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param code 响应码
	 * @param msg  响应信息
	 * @param <T>  T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(Integer code, String msg) {
		return success(code, msg, false);
	}

	/**
	 * 成功响应格式
	 *
	 * @param code     响应码
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(Integer code, String msg, boolean openMark) {
		return success(code, msg, openMark, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param code     响应码
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(Integer code, String msg, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(code, TURE, msg, null, mark)
						: create(code, TURE, msg, null, SUCCESS_MARK)
		) : create(code, TURE, msg, null, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param data 响应数据
	 * @param <T>  T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(T data) {
		return success(data, false);
	}

	/**
	 * 成功响应格式
	 *
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(T data, boolean openMark) {
		return success(data, openMark, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(T data, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(SUCCESS_CODE, TURE, SUCCESS_MSG, data, mark)
						: create(SUCCESS_CODE, TURE, SUCCESS_MSG, data, SUCCESS_MARK)
		) : create(SUCCESS_CODE, TURE, SUCCESS_MSG, data, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param msg  响应信息
	 * @param data 响应数据
	 * @param <T>  T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(String msg, T data) {
		return success(msg, data, false);
	}

	/**
	 * 成功响应格式
	 *
	 * @param msg      响应信息
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(String msg, T data, boolean openMark) {
		return success(msg, data, openMark, null);
	}

	/**
	 * 成功响应格式
	 *
	 * @param msg      响应信息
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> success(String msg, T data, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(SUCCESS_CODE, TURE, msg, data, mark)
						: create(SUCCESS_CODE, TURE, msg, data, SUCCESS_MARK)
		) : create(SUCCESS_CODE, TURE, msg, data, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param <T> T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed() {
		return failed(false);
	}

	/**
	 * 失败响应格式
	 *
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(boolean openMark) {
		return failed(openMark, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(FAILED_CODE, FALSE, FAILED_MSG, null, mark)
						: create(FAILED_CODE, FALSE, FAILED_MSG, null, FAILED_MARK)
		) : create(FAILED_CODE, FALSE, FAILED_MSG, null, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param msg 响应信息
	 * @param <T> T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(String msg) {
		return failed(msg, false);
	}

	/**
	 * 失败响应格式
	 *
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(String msg, boolean openMark) {
		return failed(msg, openMark, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(String msg, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(FAILED_CODE, FALSE, msg, null, mark)
						: create(FAILED_CODE, FALSE, msg, null, FAILED_MARK)
		) : create(FAILED_CODE, FALSE, msg, null, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param code 响应码
	 * @param msg  响应信息
	 * @param <T>  T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(Integer code, String msg) {
		return failed(code, msg, false);
	}

	/**
	 * 失败响应格式
	 *
	 * @param code     响应码
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(Integer code, String msg, boolean openMark) {
		return failed(code, msg, openMark, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param code     响应码
	 * @param msg      响应信息
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(Integer code, String msg, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(code, FALSE, msg, null, mark)
						: create(code, FALSE, msg, null, FAILED_MARK)
		) : create(code, FALSE, msg, null, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param code 响应码
	 * @param data 响应数据
	 * @param <T>  T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(Integer code, T data) {
		return failed(code, data, false);
	}

	/**
	 * 失败响应格式
	 *
	 * @param code     响应码
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(Integer code, T data, boolean openMark) {
		return failed(code, data, openMark, null);
	}

	/**
	 * 失败响应格式
	 *
	 * @param code     响应码
	 * @param data     响应数据
	 * @param openMark 开启响应标识
	 * @param mark     响应标识 [必须为：大写字母、下划线组成]
	 * @param <T>      T
	 * @return Result<T>
	 */
	public static <T> Result<T> failed(Integer code, T data, boolean openMark, String mark) {
		return openMark ? (
				judgeHas(mark) ? create(code, FALSE, FAILED_MSG, data, mark)
						: create(code, FALSE, FAILED_MSG, data, FAILED_MARK)
		) : create(code, FALSE, FAILED_MSG, data, null);
	}

	/**
	 * 响应标识参数格式异常
	 */
	public static class RIllegalArgumentException extends RuntimeException {
		public RIllegalArgumentException(String msg) {
			super(msg);
		}

		public RIllegalArgumentException(String msg, Throwable cause) {
			super(msg, cause);
		}
	}

}
