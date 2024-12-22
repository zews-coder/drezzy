package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import userservice.utils.dtos.CreateVendorDto;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByEmail(String email) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(email);
        return vendor.orElse(null);
    }

    @Override
    public List<MyEntity> get() {
        return new ArrayList<>(vendorRepository.findAll());
    }

    @Override
    public User add(MyDto myDto) {
        if (myDto instanceof CreateVendorDto createVendorDto) {
            Vendor vendor = new Vendor();
            vendor.setEmail(createVendorDto.getEmail());
            vendor.setUsername(createVendorDto.getUsername());
            vendor.setPassword(passwordEncoder.encode(createVendorDto.getPassword()));
            vendor.setRole(Role.VENDOR);
            vendor.setIsActive(true);
            vendor.setVendorName(createVendorDto.getVendorName());
            vendor.setBankAccount(createVendorDto.getBankAccount());
            vendor.setPib(createVendorDto.getPib());
            return vendorRepository.save(vendor);
        }
        return null;
    }

    @Override
    public User update(MyDto myDto) {
        if (myDto instanceof CreateVendorDto createVendorDto) {
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
        return null;
    }

    @Override
    public void activate(Long id) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow();
        vendor.setIsActive(true);
        vendorRepository.save(vendor);
    }

    @Override
    public void deactivate(Long id) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow();
        vendor.setIsActive(false);
        vendorRepository.save(vendor);
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public void empty() {
        vendorRepository.deleteAll();
    }
}
