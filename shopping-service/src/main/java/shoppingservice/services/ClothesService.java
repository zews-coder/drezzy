package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.interfaces.MyDto;
import shoppingservice.interfaces.MyEntity;
import shoppingservice.interfaces.MyService;
import shoppingservice.repositories.ClothesRepository;
import shoppingservice.utils.dtos.CreateClothesDto;

import java.util.List;

@Service
@AllArgsConstructor
public class ClothesService implements MyService {
    private final ClothesRepository clothesRepository;

    @Override
    public List<MyEntity> get() {
        return List.of();
    }

    @Override
    public MyEntity add(MyDto myDto) {
        if (myDto instanceof CreateClothesDto){

        }
        return null;
    }

    @Override
    public MyEntity update(MyDto myDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        clothesRepository.deleteById(id);
    }

    @Override
    public void empty() {
        clothesRepository.deleteAll();
    }
}
