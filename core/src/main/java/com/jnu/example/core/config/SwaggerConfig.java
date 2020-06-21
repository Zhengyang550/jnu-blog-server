package com.jnu.example.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 *  @Author: zy
 *  @Date: 2020/4/14 22:51
 *  @Description: swagger配置
 *  创建该API的基本信息（这些基本信息会展现在文档页面中）
 *  访问地址：http://127.0.0.1:9001/swagger-ui.html
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Value("${spring.application.name:core}")
    private String applicationName;

    @Value("${spring.application.version:1.0}")
    private String version;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket createRestApi() {
        //添加header参数
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(applicationName)
                        .version(version)
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jnu.example")) // 注意修改此处的包名
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://127.0.0.1:9001/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot集成swagger生成API文档")
                .description("关于quartz的一些操作接口调用")
                .contact(new Contact("zy", "https://www.cnblogs.com/zyly/", "975481319@qq.com"))
                .version("0.0.1")
                .build();
    }
}
