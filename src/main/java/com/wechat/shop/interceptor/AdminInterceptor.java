package com.wechat.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.shop.common.Constants;

/**
 * 后台管理拦截器
 * 
 * @author ZLX
 *
 */
public class AdminInterceptor implements HandlerInterceptor {

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true);
		Object admin = session.getAttribute(Constants.SESSION_ADMIN);

		// 拦截没有登录时的请求到登录页面
		if (admin == null) {
			response.sendRedirect(request.getContextPath() + "/admin/loginInit");
			return false;
		} else {
			return true;// 只有返回true才会继续向下执行，返回false取消当前请求
		}
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
