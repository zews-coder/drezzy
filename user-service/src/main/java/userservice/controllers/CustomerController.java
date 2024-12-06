package userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.domain.CreateCustomerDto;
import userservice.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCustomers() {
        try{
            return ResponseEntity.ok(customerService.getAllCustomers());
        }catch (Exception e) {
            return badRequest("failed in: /getAllCustomers");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        try {
            return ResponseEntity.ok(customerService.createCustomer(createCustomerDto));
        } catch (Exception e) {
            return badRequest("failed in: /createCustomer");
        }
    }

    private ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body(message);
    }
}
