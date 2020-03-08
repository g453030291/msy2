package com.msy.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;

@Slf4j
@Component
public class LoginFilter extends OncePerRequestFilter implements Filter {

	public static final String PATH = "/pc/login/toLogin.page";

	@Override
	protected void initFilterBean() throws ServletException {
		log.info("LoginFilter执行initFilterBean()");
		super.initFilterBean();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		log.info("访问:{}",uri);
		if(uri.contains("wx")){
			log.info("访问微信服务端");
			filterChain.doFilter(request,response);
		}else if (uri.contains("login") || uri.endsWith(".css") ||
				uri.endsWith(".js") || uri.endsWith(".png") ||
				uri.endsWith(".jpg") || uri.endsWith(".ttf") ){
			filterChain.doFilter(request,response);
		}else if(uri.contains("pc") || uri.endsWith("/")){
			log.info("访问pc端后台");
			String session = (String) request.getSession().getAttribute("user");
			if (StringUtils.isEmpty(session)){
				//session过期
				response.sendRedirect(contextPath+PATH);
				return;
			}else {
				filterChain.doFilter(request,response);
			}
		}else {
			response.sendRedirect(contextPath+PATH);
//			response.setHeader("Content-Type","text/html;charset=UTF-8");
//			response.getWriter().println("系统错误,请联系管理员!");
		}
	}

	@Override
	public void destroy() {
		log.info("LoginFilter执行destroy()");
		super.destroy();
	}
}
