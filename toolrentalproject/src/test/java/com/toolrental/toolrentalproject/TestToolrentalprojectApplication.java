package com.toolrental.toolrentalproject;

import org.springframework.boot.SpringApplication;

public class TestToolrentalprojectApplication {

	public static void main(String[] args) {
		SpringApplication.from(ToolrentalprojectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
