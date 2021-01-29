package com.course.microservice.api.server.chassis;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/chassis/security")
@Tag(name = "Security API", description = "Microservice chassis sample security API.")
public class SecurityApi {

	@GetMapping(value = "/basic_auth/one", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Demo basic authentication", description = "Demo basic authentication API.")
	public ResponseEntity<PlainMessage> demoSecurityBasicAuthOne() {
		var response = new PlainMessage("You've passed basic authentication for first sample");

		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/basic_auth/two", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Another demo for basic authentication", description = "Another demo for basic authentication API.")
	public ResponseEntity<PlainMessage> demoSecurityBasicAuthTwo(
			@RequestBody @Parameter(description = "Request body") PlainMessage requestBody) {
		var response = new PlainMessage(
				"You've passed basic authentication for second sample. Your message is : " + requestBody.getMessage());

		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/cache/time", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Demo custom security", description = "Demo custom security API.")
	public ResponseEntity<PlainMessage> demoSecurityCustom() {
		var response = new PlainMessage("Time is " + LocalTime.now());

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body(response);
	}

	@GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Demo header validation", description = "Demo header valdation API.")
	public ResponseEntity<PlainMessage> demoSecurityEmailOne() {
		var response = new PlainMessage(
				"If you can see this message, means you put valid email on header 'X-Developer-Email'.");

		return ResponseEntity.ok().body(response);
	}

}
