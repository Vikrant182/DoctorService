package com.istio.doctor.config.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Home Service")
				.description("Return the name of the author")
				.contact("sakkashy@in.ibm.com").license("IBM Corportaion")
				.licenseUrl("sakkashy@in.ibm.com").version("1.0").build();
	}
	
	private Predicate<String> postPaths() {
		return or(regex("/doctor.*"));
	}
}
