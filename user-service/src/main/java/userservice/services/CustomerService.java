package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userservice.domain.CreateCustomerDto;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerRepository.findAll());
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = new Customer();
        customer.setUsername(createCustomerDto.getUsername());
        customer.setPassword(createCustomerDto.getPassword());
        customer.setEmail(createCustomerDto.getEmail());
        customer.setFirstName(createCustomerDto.getFirstName());
        customer.setLastName(createCustomerDto.getLastName());
        customer.setPhoneNumber(createCustomerDto.getPhoneNumber());
        customer.setRole(Role.CUSTOMER);
        customer.setAddress(createCustomerDto.getAddress());
        return customerRepository.save(customer);
    }

    //change customer

    //deactivate customer

    //delete customer
}
