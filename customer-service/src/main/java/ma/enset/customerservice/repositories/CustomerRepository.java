package ma.enset.customerservice.repositories;

import ma.enset.customerservice.entities.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<customer, Long> {

}
