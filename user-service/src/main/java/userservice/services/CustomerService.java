package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class CustomerService{
    private final CustomerRepository customerRepository;

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<Customer>(customerRepository.findAll());
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = new Customer();
        customer.setEmail(createCustomerDto.getEmail());
        customer.setUsername(createCustomerDto.getUsername());
        customer.setPassword(createCustomerDto.getPassword());
        customer.setRole(Role.CUSTOMER);
        customer.setIsActive(true);
        customer.setFirstName(createCustomerDto.getFirstName());
        customer.setLastName(createCustomerDto.getLastName());
        customer.setPhoneNumber(createCustomerDto.getPhoneNumber());
        customer.setAddress(createCustomerDto.getAddress());
        customer.setCardNumber(createCustomerDto.getCardNumber());
        return customerRepository.save(customer);
    }


    //change customer

    //deactivate customer

    //delete customer
}
