package com.course.microservice.api.server;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.config.VaultConfig;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/config")
@Tag(name = "External configuration API", description = "Sample API for external configuration")
public class VaultConfigApi {

	private VaultConfig vaultConfig;

	public VaultConfigApi(VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;
	}

	@GetMapping(value = "/vault", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VaultConfig> vault() {
		return ResponseEntity.ok().body(vaultConfig);
	}

}
