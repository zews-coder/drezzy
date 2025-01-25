package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.ArticleClothes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;
import shoppingservice.repositories.ClothesRepository;
import shoppingservice.utils.dtos.CreateClothesDto;

import java.util.Date;

@Service
@AllArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public ArticleClothes addClothes(CreateClothesDto createClothesDto) {
        ArticleClothes articleClothes = new ArticleClothes();
        articleClothes.setTitle(createClothesDto.getTitle());
        articleClothes.setDescription(createClothesDto.getDescription());
        articleClothes.setPrice(createClothesDto.getPrice());
        articleClothes.setDiscount(createClothesDto.getDiscount());
        articleClothes.setSex(Sex.valueOf(createClothesDto.getSex().toString()));
        articleClothes.setSubtype(SubtypeClothes.valueOf(createClothesDto.getSubtype_clothes().toString()));
        articleClothes.setSize(createClothesDto.getSize());
        articleClothes.setCreationDate(new Date());
        return clothesRepository.save(articleClothes);
    }

    public void delete(Long id) {
        clothesRepository.deleteById(id);
    }

    public void empty() {
        clothesRepository.deleteAll();
    }
}
