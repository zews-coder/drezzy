package userservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.utils.dtos.CreateCustomerDto;
import userservice.interfaces.MyController;
import userservice.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController implements MyController {
    private final CustomerService customerService;

    @Override
    @GetMapping(path = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all Customers")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(customerService.get());
        } catch (Exception e) {
            return badRequest("/getAll");
        }
    }

    @PostMapping(path = "/createOne",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create new Customer")
    public ResponseEntity<?> createOne(@RequestBody CreateCustomerDto createCustomerDto) {
        try {
            return ResponseEntity.ok(customerService.add(createCustomerDto));
        } catch (Exception e) {
            return badRequest("/createOne");
        }
    }

    @PutMapping(path = "/updateOne",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update existing Customer")
    public ResponseEntity<?> updateOne(@RequestBody CreateCustomerDto createCustomerDto) {
        try {
            return ResponseEntity.ok(customerService.update(createCustomerDto));
        } catch (Exception e) {
            return badRequest("/updateOne");
        }
    }

    @Override
    @PutMapping(path = "/activeOne/{id}")
    @Operation(summary = "Activate Customer")
    public ResponseEntity<?> activeOne(@PathVariable Long id) {
        try {
            customerService.activate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return badRequest("/activeOne");
        }
    }

    @Override
    @PutMapping(path = "/deactivateOne/{id}")
    @Operation(summary = "Deactivate Customer")
    public ResponseEntity<?> deactivateOne(@PathVariable Long id) {
        try {
            customerService.deactivate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return badRequest("/deactivateOne");
        }
    }

    @Override
    @DeleteMapping(path = "/deleteOne/{id}")
    @Operation(summary = "Delete one Customer")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        try {
            customerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return badRequest("/deleteOne");
        }
    }

    @Override
    @DeleteMapping(path = "/deleteAll")
    @Operation(summary = "Delete all Customer")
    public ResponseEntity<?> deleteAll() {
        try {
            customerService.empty();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return badRequest("/deleteAll");
        }
    }

    @Override
    public ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body("failed in: " + message);
    }
}
