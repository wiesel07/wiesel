package com.wiesel.common.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/** 
*
* @ClassName   类名：DateConverConfig 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月4日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月4日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@Configuration
public class DateConverConfig {
	 @Bean
	    public Converter<String, Date> stringDateConvert() {
	        return new Converter<String, Date>() {
	            @Override
	            public Date convert(String source) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                Date date = null;
	                try {
	                    date = sdf.parse((String) source);
	                } catch (Exception e) {
	                    SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");
	                    try {
	                        date = sdfday.parse((String) source);
	                    } catch (ParseException e1) {
	                        e1.printStackTrace();
	                    }
	                }
	                return date;
	            }
	        };
	    }
}
