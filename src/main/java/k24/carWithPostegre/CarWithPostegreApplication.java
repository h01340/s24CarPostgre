package k24.carWithPostegre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.expression.Arrays;

import k24.carWithPostegre.model.ApplicationUser;
import k24.carWithPostegre.model.ApplicationUserRepository;
import k24.carWithPostegre.model.Car;
import k24.carWithPostegre.model.CarRepository;
import k24.carWithPostegre.model.Owner;
import k24.carWithPostegre.model.OwnerRepository;

@SpringBootApplication
public class CarWithPostegreApplication {
	private static final Logger log = LoggerFactory.getLogger(CarWithPostegreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarWithPostegreApplication.class, args);
	}
}
