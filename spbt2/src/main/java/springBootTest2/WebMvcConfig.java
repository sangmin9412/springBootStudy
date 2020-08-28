package springBootTest2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springBootTest2.Interceptor.CertificationInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CertificationInterceptor())
			.addPathPatterns("/**/*")
			.excludePathPatterns("/register/**/")
			.excludePathPatterns("/login")
			.excludePathPatterns("/test/**/");
	}
	
}
