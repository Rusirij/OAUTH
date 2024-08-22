package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication specifies that this application is a spring boot app and will start with a default tomcat server.
@SpringBootApplication
public class OAuthApplication {

	//Method and way to run the spring boot app
	public static void main(String[] args) {
		SpringApplication.run(OAuthApplication.class, args);
	}

}
