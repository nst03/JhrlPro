package com.fwzx.filter;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;


@Component
public class TokenFilter extends HttpFilter {
	private static final long serialVersionUID = 2386571986045107652L;
	private static final String OPTIONS_METHOD = "OPTIONS";

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)  throws IOException, ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			//允许跨域
			origin(request, response);

			//用户传的token
			String token = request.getParameter("token");
			String headToken = request.getHeader("token");
			if (token == null) {
				token = headToken;
			}
			if (headToken == null) {
				headToken = token;
			}

			Map<String, Object> result = new TreeMap<>();
			//获取请求地址
			String requestURI = request.getRequestURI();
			//登录
			if (requestURI.contains("logout.action") || requestURI.contains("getToken.action") ||
					requestURI.contains("swagger") || requestURI.contains(".js") || requestURI.contains(".css")
					|| requestURI.contains("/v3/api-docs") || requestURI.contains("/csrf")
					|| requestURI.contains("springfox") || "/".equals(requestURI)) {
				//放行
				chain.doFilter(request, response);
			} else {
				//如果token正确
				if (token!=null) {
					//放行
					chain.doFilter(request, response);
				} else {
					result.put("status", "200-3");
					result.put("msg", "token错误");
					String data = JSON.toJSONString(result);
					//如果token错误
					PrintWriter writer = response.getWriter();
					writer.write(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	/**
	 * 允许跨域
	 *
	 * @param servletRequest
	 * @param servletResponse
	 */
	public void origin(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String origin = servletRequest.getHeader(HttpHeaders.ORIGIN);
		if (!StringUtils.isEmpty(origin)) {
			// 允许客户端的域
			servletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
			// 允许客户端提交的Header
			String requestHeaders = servletRequest.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
			if (!StringUtils.isEmpty(requestHeaders)) {
				servletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);
			}

			// 允许客户端访问的Header
			servletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma");

			// 允许客户端携带凭证信息
			servletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

			// 允许客户端请求方法
			servletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, OPTIONS, DELETE");

			if (OPTIONS_METHOD.equalsIgnoreCase(servletRequest.getMethod())) {
				servletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
				servletResponse.setContentType(MediaType.TEXT_HTML_VALUE);
				servletResponse.setCharacterEncoding("utf-8");
				servletResponse.setContentLength(0);
				servletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "1800");
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}