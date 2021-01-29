package com.course.microservice.api.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CheckEmailFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var httpRequest = (HttpServletRequest) request;
		var httpResponse = (HttpServletResponse) response;
		var developerEmail = httpRequest.getHeader("X-Developer-Email");

		if (StringUtils.isBlank(developerEmail) || !EmailValidator.getInstance().isValid(developerEmail)) {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

			PrintWriter writer = response.getWriter();
			writer.print("{\"error\":\"Invalid email\"}");

			return;
		}

		chain.doFilter(request, response);
	}

}
