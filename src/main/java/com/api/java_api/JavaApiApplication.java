package com.api.java_api;

import com.api.java_api.entities.User;
import com.api.java_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaApiApplication {

//	private static UserRepository userRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(JavaApiApplication.class, args);

//		User user01 = User.builder().username("bori").build();
//		userRepository.save(user01);

		System.out.println("all done!");
	}

}
