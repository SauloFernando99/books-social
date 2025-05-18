package com.example.books_social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.books_social.util.Env.dotenv;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		System.setProperty("MONGO_ATLAS", dotenv.get("MONGO_ATLAS"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		SpringApplication.run(App.class, args);
	}

}
