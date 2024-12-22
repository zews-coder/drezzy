package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import userservice.utils.dtos.CreateCustomerDto;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.interfaces.MyDto;
import userservice.interfaces.MyEntity;
import userservice.interfaces.MyService;
import userservice.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements MyService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.orElse(null);
    }

    @Override
    public List<MyEntity> get() {
        return new ArrayList<>(customerRepository.findAll());
    }

    @Override
    public User add(MyDto myDto) {
        if (myDto instanceof CreateCustomerDto createCustomerDto) {
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
        return null;
    }

    @Override
    public User update(MyDto myDto) {
        if (myDto instanceof CreateCustomerDto createCustomerDto) {
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
        return null;
    }

    @Override
    public void activate(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setIsActive(true);
        customerRepository.save(customer);
    }

    @Override
    public void deactivate(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setIsActive(false);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void empty() {
        customerRepository.deleteAll();
    }
}
