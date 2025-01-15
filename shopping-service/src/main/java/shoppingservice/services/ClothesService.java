package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.repositories.ClothesRepository;

@Service
@AllArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public void delete(Long id) {
        clothesRepository.deleteById(id);
    }

    public void empty() {
        clothesRepository.deleteAll();
    }
}
