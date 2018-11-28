/**
 * 
 */
package com.wiesel.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
*
* @ClassName   类名：SwaggerConfig 
* @Description 功能说明：Swagger配置
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月20日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月20日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/


@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
	
	 //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;
	
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 指定路径处理PathSelectors.any()代表所有的路径
                .apis(RequestHandlerSelectors.basePackage("com.wiesel")) 
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()) 
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
              //  .termsOfServiceUrl("http://blog.didispace.com/")
                //创建人
                .contact(new Contact("wuj", "502393098@qq.com", "502393098@qq.com"))
                .version("1.0")
                .build();
    }
}
