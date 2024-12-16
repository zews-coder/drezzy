package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userservice.domains.dtos.CreateVendorMyDto;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.entities.Vendor;
import userservice.interfaces.MyDto;
import userservice.interfaces.MyEntity;
import userservice.interfaces.MyService;
import userservice.repositories.VendorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VendorService implements MyService {
    private final VendorRepository vendorRepository;

    public Vendor getVendorByEmail(String email) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(email);
        return vendor.orElse(null);
    }

    public List<Vendor> getAllVendors() {
        return new ArrayList<Vendor>(vendorRepository.findAll());
    }

    public Vendor createVendor(CreateVendorMyDto createVendorDto) {
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

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<MyEntity> get() {
        return List.of();
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

    }

    @Override
    public void empty() {

    }
}
