package com.returners.movies;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class MoviesApplication {

	@Bean
	public GroupedOpenApi swaggerConfiguration() {
		return GroupedOpenApi.builder()
				.group("MoviesAPI")
				.pathsToMatch("/**")
				.build();
	}

	@Bean
	public OpenAPI movieApi() {
		return new OpenAPI()
				.info(
						new Info()
								.title("Movies API")
								.description("Would like to choose a movie for a treat? This is the API for you!")
								.version("v1")
								.license(new License().
										name("Apache 2.0").
										url("http://springdoc.org")));
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

}
