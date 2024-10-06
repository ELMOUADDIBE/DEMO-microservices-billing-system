package ma.enset.billingservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    private String name;
    private String email;
}
