package com.fwzx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@EnableOpenApi
@Configuration
public class Swagger3Config {
	@Bean
	public Docket docket() {
		RequestParameterBuilder tokenPar = new RequestParameterBuilder();
		//用来统一一些公用参数，这里设置前端和后台登录的输入参数
		List<RequestParameter> pars = new ArrayList<>();
		tokenPar.name("token")
				.description("登录令牌")
				.query(simpleParameterSpecificationBuilder -> simpleParameterSpecificationBuilder
						.model(modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING))
						.defaultValue("super-user"))
				//放在header中
				.in(ParameterType.HEADER)  //参数类型为header中的参数
				.required(false)
				.build();
		pars.add(tokenPar.build());

		return new Docket(DocumentationType.OAS_30)
				.globalRequestParameters(pars)
//				.globalOperationParameters(pars)//定义全局的请求参数
//				.securityContexts(securityContexts())
//				.securitySchemes(securitySchemes())
				.apiInfo(builderApiInfo())
				.select()
				// 扫描所有带有 @ApiOperation 注解的类
//				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 扫描所有的 controller
                .apis(RequestHandlerSelectors.basePackage("com.fwzx.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo builderApiInfo() {
		return new ApiInfoBuilder()
				.title("项目接口文档")
				.description("项目接口文档")
				.version("1.0")
				.build();
	}

//	//其中 securityContexts() 与 securitySchemes() 是全局设置请求头参数，如不需要，则可以去掉。
//	/**
//	 * 配置请求头 token
//	 */
//	private List<SecurityContext> securityContexts() {
//		return Arrays.asList(SecurityContext.builder()
//				.securityReferences(Arrays.asList(SecurityReference.builder()
//						.reference("token")
//						.scopes(new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})
//						.build())).build());
//	}
//
//	/**
//	 * 配置请求头 token 参数
//	 */
//	private List<SecurityScheme> securitySchemes() {
//		return Arrays.asList(new ApiKey("token凭证", "token", "header"));
//	}

}
