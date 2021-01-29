package com.course.microservice.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuthEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void afterPropertiesSet() {
		setRealmName("microservice");
		super.afterPropertiesSet();
	}

	@Override
	public void commence(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			AuthenticationException authException) throws IOException {
		httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

		PrintWriter writer = httpResponse.getWriter();
		writer.print("{\"error\":\"Invalid basic authentication\"}");

		super.commence(httpRequest, httpResponse, authException);
	}

}
