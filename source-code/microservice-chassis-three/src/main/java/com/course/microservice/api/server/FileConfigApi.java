package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.config.FileConfig;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/config")
@Tag(name = "External configuration API", description = "Sample API for external configuration")
public class FileConfigApi {

	@Autowired
	private FileConfig fileConfig;

	@GetMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FileConfig> vault() {
		return ResponseEntity.ok().body(fileConfig);
	}

}
