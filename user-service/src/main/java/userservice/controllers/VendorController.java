package userservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.domains.dtos.CreateVendorMyDto;
import userservice.interfaces.MyController;
import userservice.interfaces.MyDto;
import userservice.services.VendorService;

@RestController
@RequestMapping("/api/v1/vendor")
@AllArgsConstructor
public class VendorController implements MyController {
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
    public ResponseEntity<?> createVendor(@RequestBody CreateVendorMyDto createVendorDto) {
        try {
            return ResponseEntity.ok(vendorService.createVendor(createVendorDto));
        }catch (Exception e){
            return badRequest("/createVendor");
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> createOne(MyDto myDto) {
        if (myDto instanceof CreateVendorMyDto) {}
        return null;
    }

    @Override
    public ResponseEntity<?> updateOne(MyDto myDto) {
        return null;
    }

//    @Override
//    public ResponseEntity<?> createOne(@RequestBody CreateVendorMyDto createVendorDto) {
//        return null;
//    }

//    @Override
//    public ResponseEntity<?> updateOne(MyDto myDto) {
//        return null;
//    }

    @Override
    public ResponseEntity<?> activeOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deactivateOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body("failed in: " + message);
    }
}
