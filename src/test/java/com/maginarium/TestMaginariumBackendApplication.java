package com.maginarium;

import org.springframework.boot.SpringApplication;

public class TestMaginariumBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(MaginariumBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
