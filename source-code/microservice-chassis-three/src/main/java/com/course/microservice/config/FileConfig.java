package com.course.microservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileConfig {

	@Value("${file.data.one}")
	private String fileDataOne;

	@Value("${file.data.two}")
	private String fileDataTwo;

	public String getFileDataOne() {
		return fileDataOne;
	}

	public String getFileDataTwo() {
		return fileDataTwo;
	}

	public void setFileDataOne(String fileDataOne) {
		this.fileDataOne = fileDataOne;
	}

	public void setFileDataTwo(String fileDataTwo) {
		this.fileDataTwo = fileDataTwo;
	}

	@Override
	public String toString() {
		return "FileConfig [fileDataOne=" + fileDataOne + ", fileDataTwo=" + fileDataTwo + "]";
	}

}
