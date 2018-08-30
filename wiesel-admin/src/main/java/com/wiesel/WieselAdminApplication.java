package com.wiesel;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wiesel.common.config.LongObjectSerializer;

/**
 *
 * @ClassName 类名：AdminApplication
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月4日
 * @author 创建人：wujian
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月4日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
@EnableCaching
public class WieselAdminApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		
		// SpringApplication.run(WieselAdminApplication.class, args);
		new SpringApplicationBuilder(WieselAdminApplication.class).bannerMode(Banner.Mode.OFF).run(args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ    wiesel-admin启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}

	// web容器中进行部署
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WieselAdminApplication.class);
	}

	
//	@Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
// 
//        };
//    }

	
//配置servlet 404、500异常跳转地址	
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//
//	        return new EmbeddedServletContainerCustomizer() {
//	            @Override
//	            public void customize(ConfigurableEmbeddedServletContainer container) {
//	        	ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/common/404.html");
//	                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, 
//	                "/common/500.html");
//	                ErrorPage errorpage = new ErrorPage("/common/500.html");
//	                container.addErrorPages(error404Page, error500Page,errorpage);
//	            }
//	        };
//	 }
	
	/**
	 * 统一路径前缀
	 * 
	 * @param dispatcherServlet
	 * @return
	 */
	// @Bean
	// public ServletRegistrationBean dispatcherRegistration(DispatcherServlet
	// dispatcherServlet) {
	// ServletRegistrationBean registration = new
	// ServletRegistrationBean(dispatcherServlet);
	// registration.setLoadOnStartup(1);
	// registration.addUrlMappings("/api/*");
	// registration.setName("api");
	// return registration;
	// }

	/**
	 * json 格式化选项
	 * 
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
		// List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		// MediaType formMedia = new MediaType("application", "x-www-form-urlencoded",
		// Charset.forName("UTF-8"));
		// MediaType jsonMedia = new MediaType("application", "json",
		// Charset.forName("UTF-8"));
		// MediaType textMedia = new MediaType("text", "*", Charset.forName("UTF-8"));
		//
		// supportedMediaTypes.add(formMedia);
		// supportedMediaTypes.add(jsonMedia);
		// supportedMediaTypes.add(textMedia);
		// fastConverter.setSupportedMediaTypes(supportedMediaTypes);
		return new HttpMessageConverters(fastConverter);
	}

	// 引入Fastjson解析json，不使用默认的jackson
	// 必须在pom.xml引入fastjson的jar包，并且版必须大于1.2.10
	// @Bean
	// public HttpMessageConverters fastJsonHttpMessageConverters() {
	// //1、定义一个convert转换消息的对象
	// FastJsonHttpMessageConverter fastConverter = new
	// FastJsonHttpMessageConverter();
	//
	// //2、添加fastjson的配置信息
	// FastJsonConfig fastJsonConfig = new FastJsonConfig();
	//
	// SerializerFeature[] serializerFeatures = new SerializerFeature[]{
	// // 输出key是包含双引号
	//// SerializerFeature.QuoteFieldNames,
	// // 是否输出为null的字段,若为null 则显示该字段
	//// SerializerFeature.WriteMapNullValue,
	// // 数值字段如果为null，则输出为0
	// SerializerFeature.WriteNullNumberAsZero,
	// // List字段如果为null,输出为[],而非null
	// SerializerFeature.WriteNullListAsEmpty,
	// // 字符类型字段如果为null,输出为"",而非null
	// SerializerFeature.WriteNullStringAsEmpty,
	// // Boolean字段如果为null,输出为false,而非null
	// SerializerFeature.WriteNullBooleanAsFalse,
	// // Date的日期转换器
	// SerializerFeature.WriteDateUseDateFormat,
	// // 循环引用
	// SerializerFeature.DisableCircularReferenceDetect,
	// };
	//
	// fastJsonConfig.setSerializerFeatures(serializerFeatures);
	// fastJsonConfig.setCharset(Charset.forName("UTF-8"));
	//
	// //3、在convert中添加配置信息
	// fastConverter.setFastJsonConfig(fastJsonConfig);
	//
	// //4、将convert添加到converters中
	// HttpMessageConverter<?> converter = fastConverter;
	//
	// return new HttpMessageConverters(converter);
	// }

}
