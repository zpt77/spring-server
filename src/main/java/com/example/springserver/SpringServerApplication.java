package com.example.springserver;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController
public class SpringServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}

	private LocalDateTime startupTime;

	@PostConstruct
	public void init() {
		startupTime = LocalDateTime.now();
	}

	@GetMapping("/")
	public ResponseEntity<String> main() {
		String html = "<html><body><h1>Spring Server is running!</h1><p>Started at: " + getStartupTime() + "</p></body></html>";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<>(html, headers, HttpStatus.OK);
	}

	private String getStartupTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return startupTime.format(formatter);
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

