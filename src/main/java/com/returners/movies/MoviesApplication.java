package com.returners.movies;

import com.returners.movies.model.Role;
import com.returners.movies.service.RoleService;
import com.twilio.Twilio;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	@Bean
	CommandLineRunner run (RoleService roleService){
		return args ->{
//		    roleService.addRole(new Role(null,"VIEWER"));
//			roleService.addRole(new Role(null,"MANAGER"));
//			roleService.addRole(new Role(null,"ADMIN"));

//			roleService.addRoleToUser(1L,1L);
//			roleService.addRoleToUser(1L,2L);
//			roleService.addRoleToUser(2L,1L);
//			roleService.addRoleToUser(3L,3L);
		};
	}

}
