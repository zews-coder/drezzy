package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.ArticleShoes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeShoes;
import shoppingservice.repositories.ShoesRepository;
import shoppingservice.utils.dtos.CreateShoesDto;

import java.util.Date;

@Service
@AllArgsConstructor
public class ShoesService {
    private ShoesRepository shoesRepository;

    public ArticleShoes addShoes(CreateShoesDto createShoesDto) {
        ArticleShoes articleShoes = new ArticleShoes();
        articleShoes.setTitle(createShoesDto.getTitle());
        articleShoes.setDescription(createShoesDto.getDescription());
        articleShoes.setPrice(createShoesDto.getPrice());
        articleShoes.setDiscount(createShoesDto.getDiscount());
        articleShoes.setSex(Sex.valueOf(createShoesDto.getSex().toString()));
        articleShoes.setSubtype(SubtypeShoes.valueOf(createShoesDto.getSubtype().toString()));
        articleShoes.setSize(createShoesDto.getSize());
        articleShoes.setCreationDate(new Date());
        return shoesRepository.save(articleShoes);
    }

    public void delete(Long id) {
        shoesRepository.deleteById(id);
    }

    public void empty() {
        shoesRepository.deleteAll();
    }
}
