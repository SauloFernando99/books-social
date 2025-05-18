package com.example.books_social;

import com.example.books_social.util.Env;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// MONGO_ATLAS
		String mongoUri = System.getenv("MONGO_ATLAS");
		if (mongoUri == null) {
			mongoUri = Env.get("MONGO_ATLAS");
		}
		System.setProperty("MONGO_ATLAS", mongoUri);

		// JWT_SECRET
		String jwtSecret = System.getenv("JWT_SECRET");
		if (jwtSecret == null) {
			jwtSecret = Env.get("JWT_SECRET");
		}
		System.setProperty("JWT_SECRET", jwtSecret);

		SpringApplication.run(App.class, args);
	}

}
