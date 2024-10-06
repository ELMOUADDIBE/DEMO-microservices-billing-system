package ma.enset.customerservice;

import ma.enset.customerservice.entities.Product;
import ma.enset.customerservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            Stream.of("TV LG", "HP Laptop", "Printer", "Smartphone").forEach(name -> {
                productRepository.save(Product.builder()
                        .name(name)
                        .price(Math.random() * 1000)
                        .quantity((int) (Math.random() * 100))
                        .build());
            });
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
