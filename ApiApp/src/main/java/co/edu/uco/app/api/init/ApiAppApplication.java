package co.edu.uco.app.api.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.app"})
public class ApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAppApplication.class, args);
	}

}
