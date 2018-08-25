package com.wiesel.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.exceptions.TemplateInputException;

import lombok.extern.slf4j.Slf4j;
import wiesel.common.api.ApiResult;
import wiesel.common.exception.ApiException;

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
public class GlobalExceptionHandler {

	/**
	 * 拦截Exception类的异常
	 * 
	 * @param <T>
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public <T> ApiResult<T> exceptionHandler(Exception e) {
		e.printStackTrace();
		return ApiResult.error("系统未知异常");
	}

	// @ExceptionHandler(value = Exception.class)
	// public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception
	// e) throws Exception {
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.addObject("exception", e.getMessage());
	// modelAndView.addObject("url", req.getRequestURL());
	// modelAndView.setViewName("error");
	// return modelAndView;
	// }

	/**
	 * 拦截Exception类的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ApiException.class)
	@ResponseBody
	public <T> ApiResult<T> exceptionHandler(ApiException e) {
		log.info("ApiException异常");

		return ApiResult.error(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public <T> ApiResult<T> handleBindException(MethodArgumentNotValidException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());

		return ApiResult.error(fieldError.getDefaultMessage());

	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public <T> ApiResult<T> handleBindException(BindException ex) {
		// 校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为
		// BeanPropertyBindingResult
		FieldError fieldError = ex.getBindingResult().getFieldError();

		log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
		return ApiResult.error(fieldError.getDefaultMessage());
	}

	@ExceptionHandler(AuthorizationException.class)
	public String handleBindException(AuthorizationException ex) {

		ex.printStackTrace();
		log.info("权限校验异常:{}({})", ex.getMessage());
		return "error";
	}

	@ExceptionHandler(TemplateInputException.class)
	public String exceptionHandler(TemplateInputException ex) {

		ex.printStackTrace();
		log.info("模板解析异常:{}({})", ex.getMessage());
		return "error";
	}

}
