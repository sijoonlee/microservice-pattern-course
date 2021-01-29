package com.course.microservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "myvault", ignoreUnknownFields = true, ignoreInvalidFields = true)
@EnableConfigurationProperties({ VaultConfig.class })
public class VaultConfig {

	@Value("${myvault.data.one}")
	private String dataOne;

	@Value("${myvault.data.two}")
	private String dataTwo;

	@Value("${myvault.sample.password}")
	private String password;

	@Value("${myvault.sample.username}")
	private String username;

	public String getDataOne() {
		return dataOne;
	}

	public String getDataTwo() {
		return dataTwo;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setDataOne(String dataOne) {
		this.dataOne = dataOne;
	}

	public void setDataTwo(String dataTwo) {
		this.dataTwo = dataTwo;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "VaultConfig [dataOne=" + dataOne + ", dataTwo=" + dataTwo + ", password=" + password + ", username="
				+ username + "]";
	}

}
