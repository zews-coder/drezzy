package userservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.domain.CreateVendorDto;
import userservice.services.VendorService;

@RestController
@RequestMapping("/api/v1/vendor")
@AllArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllVendors() {
        try{
            return ResponseEntity.ok(vendorService.getAllVendors());
        }catch (Exception e){
            return badRequest("/getAllVendors");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createVendor(@RequestBody CreateVendorDto createVendorDto) {
        try {
            return ResponseEntity.ok(vendorService.createVendor(createVendorDto));
        }catch (Exception e){
            return badRequest("/createVendor");
        }
    }

    private ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body("failed in: " + message);
    }
}
