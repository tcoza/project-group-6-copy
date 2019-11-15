package ca.mcgill.ecse321.tutoringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class TutoringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutoringAppApplication.class, args);
	}
	
	@CrossOrigin
	@RequestMapping("/")
	public String greeting(){
	    return "<b>Hello world!</b>";
	}
}
