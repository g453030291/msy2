package com.msy.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginFilter extends OncePerRequestFilter implements Filter {

	@Override
	protected void initFilterBean() throws ServletException {
		log.info("LoginFilter执行initFilterBean()");
		super.initFilterBean();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String uri = request.getRequestURI();
		log.info("访问:{}",uri);
		if(uri.contains("wx")){
			System.out.println("访问微信服务端");
		}
		filterChain.doFilter(request,response);
	}

	@Override
	public void destroy() {
		log.info("LoginFilter执行destroy()");
		super.destroy();
	}
}
