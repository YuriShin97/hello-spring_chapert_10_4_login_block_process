package com.ktdsuniversity.edu.hellospringhomework.common.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// application.yml 에서 설정하지 못하는 디테일한 설정을 위한 어노테이션
// 설정 상의 우선순위가 변경되었기 때문(@Configuration > application.yml)
// Spring Bean 을 수동으로 생성하는 기능.
@Configuration
// Spring WebMVC 에 필요한 다양한 요소를 활성화시키는 어노테이션
//		- Spring Validator
//		- Spring Inteceptor
//		- ...
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	/**
	 * Auto DI: @Component
	 * Manual DI: @Bean
	 *  -> 객체 생성을 Spring 이 아닌 개발자가 직접 하는 것.
	 * @return
	 */
	@Bean
	// 자동 injection 이 안되므로 수동 injection 필요. @Autowired 불가.
	SHA createSHAInstance() {
		SHA sha = new SHA();
		
		return sha;
	}
	
	
	
	

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	/**
	 * Static Resources 설정도 함께 해주어야 한다.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**") // http://localhost:8080/css/common/common.css
				.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/static/js/");
	}
	
}
