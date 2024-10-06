package ma.enset.billingservice;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.model.Customer;
import ma.enset.billingservice.model.Product;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import ma.enset.billingservice.services.CustomerRestClient;
import ma.enset.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient,
                            ProductItemRepository productItemRepository) {
        return args -> {
            Collection<Product> products = productRestClient.allProducts().getContent();
            Long customerId = 1L;

            Customer customer = customerRestClient.findCustomerById(customerId);
            if (customer == null) throw new RuntimeException("Customer Not Found");

            Bill bill = Bill.builder()
                    .customerId(customerId)
                    .billingDate(new Date())
                    .build();
            Bill savedBill = billRepository.save(bill);

            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1 + new Random().nextInt(10));
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });
        };
    }

}
