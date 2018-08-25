package wiesel.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @ClassName 类名：ApiController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月24日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月24日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
public class ApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * <p>
	 * 请求成功
	 * </p>
	 *
	 * @param data
	 *            数据内容
	 * @param <T>
	 *            对象泛型
	 * @return
	 */
	protected <T> ApiResult<T> success(T data) {
		return ApiResult.ok(data);
	}

	/**
	 * <p>
	 * 请求失败
	 * </p>
	 *
	 * @param msg
	 *            提示内容
	 * @return
	 */
	protected ApiResult<Object> failed(String msg) {
		return ApiResult.error(msg);
	}

	/**
	 * <p>
	 * 请求失败
	 * </p>
	 *
	 * @param errorCode
	 *            请求错误码
	 * @return
	 */
	protected ApiResult<Object> failed(IErrorCode errorCode) {
		return ApiResult.error(errorCode);
	}

}
