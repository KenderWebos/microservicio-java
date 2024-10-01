package com.api.java_api;

import com.api.java_api.entities.User;
import com.api.java_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApiApplication {

	//private static UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JavaApiApplication.class, args);

		//User user01 = User.builder().nombre("bori").edad(99).build();

		//userRepository.save(user01);
	}

}
