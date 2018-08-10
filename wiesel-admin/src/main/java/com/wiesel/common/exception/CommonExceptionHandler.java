package com.wiesel.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiesel.common.utils.R;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：CommonExceptionHandler
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月9日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月9日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

	/**
	 * 拦截Exception类的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R exceptionHandler(Exception e) {
		e.printStackTrace();
		return R.error();
	}

	/**
	 * 拦截Exception类的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public R exceptionHandler(CommonException e) {
		log.info("CommonException异常");
		
		return R.error(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R handleBindException(MethodArgumentNotValidException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());

		return R.error(01002, fieldError.getDefaultMessage());

	}

	@ExceptionHandler(BindException.class)
	public R handleBindException(BindException ex) {
		// 校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
		FieldError fieldError = ex.getBindingResult().getFieldError();

		log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
		return R.error(01002, fieldError.getDefaultMessage());
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public void handleBindException(AuthorizationException ex) {
		
		ex.printStackTrace();
		log.info("权限校验异常:{}({})", ex.getMessage());
	}
	
	
}
