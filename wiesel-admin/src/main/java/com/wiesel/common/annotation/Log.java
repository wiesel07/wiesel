package com.wiesel.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/** 
*
* @ClassName   类名：Log 
* @Description 功能说明：日志注解类
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月25日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月25日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface  Log {
	 /**
     * 日志描述，这里使用了@AliasFor 别名。spring提供的
     * @return
     */
    @AliasFor("desc")
    String value() default "";
    
   
    
    /**
     * 是否不记录日志
     * @return
     */
    boolean ignore() default false;
}
