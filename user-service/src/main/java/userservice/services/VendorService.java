package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userservice.domain.CreateVendorDto;
import userservice.entities.Role;
import userservice.entities.Vendor;
import userservice.repositories.VendorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VendorService {
    private final VendorRepository vendorRepository;

    public Vendor getVendorByEmail(String email) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(email);
        return vendor.orElse(null);
    }

    public List<Vendor> getAllVendors() {
        return new ArrayList<Vendor>(vendorRepository.findAll());
    }

    public Vendor createVendor(CreateVendorDto createVendorDto) {
        Vendor vendor = new Vendor();
        vendor.setEmail(createVendorDto.getEmail());
        vendor.setUsername(createVendorDto.getUsername());
        vendor.setPassword(createVendorDto.getPassword());
        vendor.setRole(Role.VENDOR);
        vendor.setIsActive(true);
        vendor.setVendorName(createVendorDto.getVendorName());
        vendor.setBankAccount(createVendorDto.getBankAccount());
        vendor.setPib(createVendorDto.getPib());
        return vendorRepository.save(vendor);
    }
}
