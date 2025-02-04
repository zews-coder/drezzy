package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shoppingservice.enitites.ArticleShoes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeShoes;
import shoppingservice.repositories.ShoesRepository;
import shoppingservice.utils.dtos.CreateShoesDto;

import java.io.IOException;
import java.util.Date;

@Service
@AllArgsConstructor
public class ShoesService {
    private ShoesRepository shoesRepository;

    public ArticleShoes addShoes(CreateShoesDto createShoesDto, MultipartFile image) throws IOException {
        ArticleShoes articleShoes = new ArticleShoes();
        articleShoes.setTitle(createShoesDto.getTitle());
        articleShoes.setDescription(createShoesDto.getDescription());
        articleShoes.setPrice(createShoesDto.getPrice());
        articleShoes.setDiscount(0);
        articleShoes.setSex(Sex.valueOf(createShoesDto.getSex().toString()));
        articleShoes.setSubtype(SubtypeShoes.valueOf(createShoesDto.getSubtype_shoes().toString()));
        articleShoes.setSize(createShoesDto.getSize());
        articleShoes.setCreationDate(new Date());
        if (image != null) {
            articleShoes.setImage(image.getBytes());
        }
        return shoesRepository.save(articleShoes);
    }

    public void delete(Long id) {
        shoesRepository.deleteById(id);
    }

    public void empty() {
        shoesRepository.deleteAll();
    }
}
