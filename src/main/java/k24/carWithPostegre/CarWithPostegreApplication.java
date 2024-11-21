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

	 @Bean
	public CommandLineRunner demoData(CarRepository carRepository,
			OwnerRepository ownerRepository,
			ApplicationUserRepository applicationUserRepository) {
		return (args) -> {

			log.info("luodaan pari sovelluksen käyttäjää: toinen admin/admin ja toinen user/user");
			// public ApplicationUser(String firstName, String lastName, String role, String
			// username, String passwordHash)
			ApplicationUser user1 = new ApplicationUser("Otto", "Pellikka", "USER", "user",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6");
			ApplicationUser user2 = new ApplicationUser("Minna", "Pellikka", "ADMIN", "admin",
					"$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2");
			applicationUserRepository.save(user1);
			applicationUserRepository.save(user2);
					
			log.info("save owners");
			ownerRepository.save(new Owner("Kia", "Watson", 1978));
			ownerRepository.save(new Owner("Aku", "Ankka", 1940));
			ownerRepository.save(new Owner("Iines", "Ankka", 1941));
			log.info("print owners");
			for (Owner owner : ownerRepository.findAll()) {
				log.info(owner.toString());
			}

			log.info("save some cars");
			carRepository.save(new Car("Ford", "Mustang", ownerRepository.findByLastName("Watson").get(0)));
			carRepository.save(new Car("Nissan", "Leaf", ownerRepository.findByLastName("Watson").get(0)));
			carRepository.save(new Car("Toyota", "Prius", ownerRepository.findByLastName("Ankka").get(0)));
			carRepository.save(new Car("Toyota", "Prius"));

			log.info("print car information");
			for (Car car : carRepository.findAll()) {
				log.info(car.toString());
			}

		};
	}
}
