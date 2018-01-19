package com.wechat.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wechat.shop.interceptor.AdminInterceptor;

@Configuration
public class AdminInterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/loginInit", "/admin/adminLogin", "/admin/getAuthImage");
		super.addInterceptors(registry);
	}
}
