package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import userservice.utils.dtos.CreateCustomerDto;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.repositories.CustomerRepository;
import userservice.utils.dtos.UserInfoDto;

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

    public void add(CreateCustomerDto createCustomerDto) {
            Customer customer = new Customer();
            customer.setEmail(createCustomerDto.getEmail());
            customer.setUsername(createCustomerDto.getUsername());
            customer.setPassword(passwordEncoder.encode(createCustomerDto.getPassword()));
            customer.setRole(Role.CUSTOMER);
            customer.setIsActive(true);
            customer.setFirstName(createCustomerDto.getFirstName());
            customer.setLastName(createCustomerDto.getLastName());

            if (createCustomerDto.getAddress() != null) {
                customer.setAddress(createCustomerDto.getAddress());
            }

            if (createCustomerDto.getCardInfo() != null) {
                customer.setCardInfo(createCustomerDto.getCardInfo());
            }

           customerRepository.save(customer);
    }

    public Customer update(CreateCustomerDto createCustomerDto, Long id) {
        Customer customer = customerRepository.findById(id).get();

        customer.setUsername(createCustomerDto.getUsername());
        customer.setEmail(createCustomerDto.getEmail());
        if (createCustomerDto.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(createCustomerDto.getPassword()));
        }
        customer.setFirstName(createCustomerDto.getFirstName());
        customer.setLastName(createCustomerDto.getLastName());
        if (createCustomerDto.getAddress() != null) {
            customer.setAddress(createCustomerDto.getAddress());
        }else {
            customer.setAddress(null);
        }
        if (createCustomerDto.getCardInfo() != null) {
            customer.setCardInfo(createCustomerDto.getCardInfo());
        }else {
            customer.setCardInfo(null);
        }

        return customerRepository.save(customer);
    }

    public UserInfoDto findCustomerInfo(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setFirstName(customer.getFirstName());
        userInfoDto.setLastName(customer.getLastName());
        userInfoDto.setEmail(customer.getEmail());
        userInfoDto.setUsername(customer.getUsername());
        if (customer.getAddress() != null) {
            userInfoDto.setAddress(customer.getAddress());
        }
        if (customer.getCardInfo() != null) {
            userInfoDto.setCardInfo(customer.getCardInfo());
        }

        return userInfoDto;
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
