package pl.mmprogr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

/**
 * Created by ggere on 21.12.2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("pl.mmprogr.controllers"))
				.paths(any())
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfo(
				"Answer Site REST API",
				"written in Spring Boot",
				"1.0",
				"Terms of service",
				new Contact("Mateusz Macholl", "https://github.com/MMprogr", "mmprogr@gmail.com"),
				"",
				"");
	}
}
