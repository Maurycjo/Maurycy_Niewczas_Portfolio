package pl.edu.pwr.internetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class InternetAppApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(InternetAppApplication.class)
				.headless(false)
				.run(args);
	}

}
