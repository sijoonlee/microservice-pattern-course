package com.course.microservice.api.server;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoApi {

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
	public String pong(HttpServletRequest request) {
		var sb = new StringBuilder();

		sb.append("Echo from : " + applicationName);
		sb.append("\n");
		sb.append("\n");
		sb.append("Request URL : " + request.getRequestURL());
		sb.append("\n");
		sb.append("\n");
		sb.append("Request headers : ");

		request.getHeaderNames().asIterator().forEachRemaining(h -> {
			sb.append("    " + h + " : " + request.getHeader(h));
			sb.append("\n");
		});

		sb.append("\n");
		sb.append("Query string : " + request.getQueryString());

		return sb.toString();
	}

}
