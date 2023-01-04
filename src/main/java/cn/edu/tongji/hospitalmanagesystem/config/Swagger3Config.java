package cn.edu.tongji.hospitalmanagesystem.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author tangshuo
 * @version 1.0.0
 * @ClassName Swagger3Config.java
 * @Description TODO
 * @createTime 2022年01月23日 15:22:00
 */
@Configuration
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("医院管理系统后端")
                .description("RESTFul Api")
                .contact(new Contact("Shuo Tang", "https://github.com/ts666823", "823206295@qq.com"))
                .version("1.00")
                .build();
    }
}

