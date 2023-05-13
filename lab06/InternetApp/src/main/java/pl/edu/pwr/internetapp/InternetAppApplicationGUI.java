package pl.edu.pwr.internetapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class InternetAppApplicationGUI {

	public static void main(String[] args) {
		new SpringApplicationBuilder(InternetAppApplicationGUI.class)
				.headless(false)
				.run(args);
	}

}
