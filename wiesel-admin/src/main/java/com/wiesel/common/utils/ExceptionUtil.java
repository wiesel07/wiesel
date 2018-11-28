package com.wiesel.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import lombok.experimental.UtilityClass;

/** 
*
* @ClassName   类名：ExceptionUtil 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年9月1日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年9月1日   wuj     创建该类功能。
*
***********************************************************************
*</p>
*/
@UtilityClass
public class ExceptionUtil {

	/**
	 * 
	 * <p>函数名称：getExceptionStackTrace        </p>
	 * <p>功能说明：获取异常的详细信息（调用堆栈）
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param e
	 * @return
	 *
	 * @date   创建时间：2018年9月1日
	 * @author 作者：wuj
	 */
	public String getExceptionStackTrace(Exception e) {
			StringWriter sw = null;
			PrintWriter pw = null;
			try {
				sw = new StringWriter();
				pw = new PrintWriter(sw);
				// 将出错的栈信息输出到printWriter中
				e.printStackTrace(pw);
				pw.flush();
				sw.flush();
			} finally {
				if (sw != null) {
					try {
						sw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (pw != null) {
					pw.close();
				}
			}
			return sw.toString();
		}


}
