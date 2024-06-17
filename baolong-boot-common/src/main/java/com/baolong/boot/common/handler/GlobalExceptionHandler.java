package com.baolong.boot.common.handler;

import com.baolong.boot.common.entity.response.Result;
import com.baolong.boot.common.enums.RespEnum;
import com.baolong.boot.common.exception.BizException;
import com.baolong.boot.common.function.RFLimit.RFLimitAspect;
import com.baolong.boot.common.function.RSLock.RSLockAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author Baolong 2024/6/6 19:49
 * @version 1.0
 * @since 1.8
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 请求频率限制异常
	 *
	 * @param e RFLimitAspect.RFLimitException.class
	 * @return Result<RFLimitAspect.RFLimitException>
	 */
	@ExceptionHandler(RFLimitAspect.RFLimitException.class)
	public Result<RFLimitAspect.RFLimitException> rfLimitException(RFLimitAspect.RFLimitException e) {
		log.error("【请求频率限制异常】: {}", e.getMessage());
		return Result.failed(e.getMessage(), true);
	}

	/**
	 * Redisson 分布式锁异常
	 *
	 * @param e RSLockAspect.RSLockException.class
	 * @return Result<RSLockAspect.RSLockException>
	 */
	@ExceptionHandler(RSLockAspect.RSLockException.class)
	public Result<RSLockAspect.RSLockException> rfLimitException(RSLockAspect.RSLockException e) {
		log.error("【分布式锁异常】: {}", e.getMessage());
		return Result.failed(e.getMessage(), true);
	}

	/**
	 * 响应标识格式异常
	 *
	 * @param e Result.RIllegalArgumentException
	 * @return Result<Result.RIllegalArgumentException>
	 */
	@ExceptionHandler(Result.RIllegalArgumentException.class)
	public Result<Result.RIllegalArgumentException> illegalException(Result.RIllegalArgumentException e) {
		log.error("【响应标识格式异常】: {}", e.getMessage());
		return Result.custom(RespEnum.RESP_MARK_ERROR.getCode(), false, RespEnum.RESP_MARK_ERROR.getMsg(), e,
				true, Result.FAILED_MARK);
	}

	/**
	 * 处理提交类型为 JSON 对象的异常
	 *
	 * @param e       the exception
	 * @param headers the headers to be written to the response
	 * @param status  the selected response status
	 * @param request the current request
	 * @return ResponseEntity<Object>
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("【JSON参数异常】: {}", e.getMessage());
		// 收集异常信息
		Map<String, String> errors = new HashMap<>();
		e.getFieldErrors().forEach(p -> {
			errors.put(p.getField(), p.getDefaultMessage());
		});
		return new ResponseEntity<>(Result.custom(RespEnum.JSON_PARAM_ERROR.getCode(), false,
				RespEnum.JSON_PARAM_ERROR.getMsg(), errors), HttpStatus.OK);
	}

	/**
	 * 处理提交类型为 form 表单的异常
	 *
	 * @param e       the exception
	 * @param headers the headers to be written to the response
	 * @param status  the selected response status
	 * @param request the current request
	 * @return ResponseEntity<Object>
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("【form表单参数异常】: {}", e.getMessage());
		// 收集异常信息
		Map<String, String> errors = new HashMap<>();
		e.getFieldErrors().forEach(p -> {
			errors.put(p.getField(), p.getDefaultMessage());
		});
		return new ResponseEntity<>(Result.custom(RespEnum.FORM_PARAM_ERROR.getCode(), false,
				RespEnum.FORM_PARAM_ERROR.getMsg(), errors), HttpStatus.OK);
	}

	/**
	 * 处理提交类型为 URL 的异常
	 *
	 * @param e ConstraintViolationException
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException e) {
		log.error("【URL参数异常】: {}", e.getMessage());
		// 收集异常信息
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		Map<String, String> errors = new HashMap<>();
		violations.forEach(p -> {
			String fieldName = null;
			for (Path.Node node : p.getPropertyPath()) {
				fieldName = node.getName();
			}
			errors.put(fieldName, p.getMessage());
		});
		return new ResponseEntity<>(Result.custom(RespEnum.URL_PARAM_ERROR.getCode(), false,
				RespEnum.URL_PARAM_ERROR.getMsg(), errors), HttpStatus.OK);
	}

	/**
	 * 业务异常
	 *
	 * @param e BizException
	 * @return Result<BizException>
	 */
	@ExceptionHandler(BizException.class)
	public Result<BizException> bizException(BizException e) {
		log.error("【业务异常】: {}", e.getMessage());
		return Result.failed(e.getCode(), e.getMessage(), true);
	}

	/**
	 * 未知类型异常
	 *
	 * @param e RuntimeException
	 * @return Result<RuntimeException>
	 */
	@ExceptionHandler(RuntimeException.class)
	public Result<String> runtimeException(RuntimeException e) {
		log.error("【其他类型异常】: {}", e.getMessage(), e);
		return Result.custom(RespEnum.UNKNOWN_ERROR.getCode(), false, RespEnum.UNKNOWN_ERROR.getMsg(), e.getMessage(),
				true, Result.FAILED_MARK);
	}

	/**
	 * 系统异常
	 *
	 * @param e Exception
	 * @return Result<Exception>
	 */
	@ExceptionHandler(Exception.class)
	public Result<Exception> systemException(Exception e) {
		log.error("【未知系统异常】: {}", e.getMessage(), e);
		return Result.custom(RespEnum.SYSTEM_ERROR.getCode(), false, RespEnum.SYSTEM_ERROR.getMsg(), e,
				true, Result.FAILED_MARK);
	}

}
