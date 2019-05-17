package br.com.cmabreu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try {
			new DemoClass().run();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

}
