package k24.carWithPostegre.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class WebClientRest {
    private static final Logger log = LoggerFactory.getLogger(CarController.class);

    @GetMapping("webClient")
    public void showInfoFromJsonPlaceholder() {
        log.info("restTemplate");
        // Create a WebClient instance
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

        // Make a GET request
        Mono<String> response = webClient.get()
                .uri("/todos") // Specify the endpoint
                .retrieve() // Initiate the request
                .bodyToMono(String.class); // Convert the response body to a String

        // Subscribe to the response and print it
        response.subscribe(System.out::println);
    }
    @GetMapping("webClient2")
    public void showInfoFromJsonPlaceholder2() {
        log.info("webClient2");
        // Create a WebClient instance
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

        // Make a GET request
        Mono<String> response = webClient.get()
                .uri("/todos") // Specify the endpoint
                .retrieve() // Initiate the request
                .bodyToMono(String.class); // Convert the response body to a String

        // Subscribe to the response and print it
        response.subscribe(System.out::println);
    }

}
