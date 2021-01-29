package com.course.microservice.api.server.chassis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/chassis")
@Tag(name = "External Config API", description = "Microservice chassis sample external config API.")
public class ExternalConfigApi {

	@Value("${microservice.title:Default Title}")
	private String title;

	@GetMapping(value = "/title", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get title", description = "Get title from external configuration, "
			+ "or get default title if no external configuration defined.")
	public ResponseEntity<PlainMessage> demoTitle() {
		var response = new PlainMessage(title);
		return ResponseEntity.ok().body(response);
	}

}
