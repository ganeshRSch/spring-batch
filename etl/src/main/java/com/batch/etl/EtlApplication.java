package com.batch.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EtlApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(EtlApplication.class, args)));
	}

}
