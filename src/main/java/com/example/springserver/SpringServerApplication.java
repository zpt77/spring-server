package com.example.springserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginData loginData) {
		String username = loginData.getUsername();
		String password = loginData.getPassword();
		if ("admin".equals(username) && "spring".equals(password)) {
			return new ResponseEntity<>("{\"message\": \"Success\"}", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{\"message\": \"Failure\"}", HttpStatus.UNAUTHORIZED);
		}
	}

	static class LoginData {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}

