package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userservice.domains.dtos.CreateCustomerDto;
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
        return null;
    }

    @Override
    public User update(MyDto myDto) {
        return null;
    }

    @Override
    public void activate(Long id) {

    }

    @Override
    public void deactivate(Long id) {

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
