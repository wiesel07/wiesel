package com.wiesel;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wiesel.common.config.LongObjectSerializer;

/** 
*
* @ClassName   类名：AdminApplication 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月4日
* @author      创建人：wujian
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月4日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@EnableTransactionManagement
@ServletComponentScan
//@MapperScan("com.wiesel.**.mapper")
@SpringBootApplication
@EnableCaching
public class WieselAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(WieselAdminApplication.class, args);
		 System.out.println("ヾ(◍°∇°◍)ﾉﾞ    wiesel-admin启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
	                " ______                    _   ______            \n" +
	                "|_   _ \\                  / |_|_   _ `.          \n" +
	                "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
	                "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
	                " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
	                "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}

	
	/**
	 * 统一路径前缀
	 * @param dispatcherServlet
	 * @return
	 */
//	@Bean
//	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//		registration.setLoadOnStartup(1);
//		registration.addUrlMappings("/api/*");
//		registration.setName("api");
//		return registration;
//	}

	/**
	 * json 格式化选项
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
		SerializeConfig config = new SerializeConfig();
		config.put(Long.class, new LongObjectSerializer());
		fastJsonConfig.setSerializeConfig(config);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		MediaType formMedia = new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8"));
		MediaType jsonMedia = new MediaType("application", "json", Charset.forName("UTF-8"));
		MediaType textMedia = new MediaType("text", "*", Charset.forName("UTF-8"));

		supportedMediaTypes.add(formMedia);
		supportedMediaTypes.add(jsonMedia);
		supportedMediaTypes.add(textMedia);		
		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
		return new HttpMessageConverters(fastConverter);
	}
}
