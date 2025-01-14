package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import userservice.utils.dtos.CreateCustomerDto;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    public User getByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.orElse(null);
    }

    public List<Customer> get() {
        return new ArrayList<>(customerRepository.findAll());
    }

    public User add(CreateCustomerDto createCustomerDto) {
            Customer customer = new Customer();
            customer.setEmail(createCustomerDto.getEmail());
            customer.setUsername(createCustomerDto.getUsername());
            customer.setPassword(passwordEncoder.encode(createCustomerDto.getPassword()));
            customer.setRole(Role.CUSTOMER);
            customer.setIsActive(true);
            customer.setFirstName(createCustomerDto.getFirstName());
            customer.setLastName(createCustomerDto.getLastName());
            customer.setPhoneNumber(createCustomerDto.getPhoneNumber());
            customer.setAddress(createCustomerDto.getAddress());
            customer.setCardNumber(createCustomerDto.getCardNumber());
            return customerRepository.save(customer);
    }

    public User update(CreateCustomerDto createCustomerDto) {

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

    public void activate(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setIsActive(true);
        customerRepository.save(customer);
    }

    public void deactivate(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setIsActive(false);
        customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public void empty() {
        customerRepository.deleteAll();
    }
}
