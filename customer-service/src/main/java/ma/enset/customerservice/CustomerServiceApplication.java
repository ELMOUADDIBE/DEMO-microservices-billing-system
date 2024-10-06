package ma.enset.customerservice;

import ma.enset.customerservice.entities.customer;
import ma.enset.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(customer.class); // Expose the id field
            Stream.of("A", "B", "C").forEach(c -> {
                customerRepository.save(customer.builder().name(c).email(c + "@gmail.com").build());
            });
            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
