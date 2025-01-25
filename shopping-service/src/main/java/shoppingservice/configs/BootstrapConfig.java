package shoppingservice.configs;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shoppingservice.enitites.ArticleClothes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;
import shoppingservice.repositories.ClothesRepository;

import java.util.Date;

@Component
@AllArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final ClothesRepository clothesRepository;

    @Override
    public void run(String... args) throws Exception {

        if (clothesRepository.count() == 0) {
            ArticleClothes articleClothes1 = new ArticleClothes();
            articleClothes1.setTitle("Majcica");
            articleClothes1.setDescription("Preljepa majcica po povoljnoj ceni");
            articleClothes1.setCreationDate(new Date());
            articleClothes1.setPrice(50.0);
            articleClothes1.setSex(Sex.MEN);
            articleClothes1.setSubtype(SubtypeClothes.SHIRTS);
            articleClothes1.setSize("M");
            clothesRepository.save(articleClothes1);

            ArticleClothes articleClothes2 = new ArticleClothes();
            articleClothes2.setTitle("Lone");
            articleClothes2.setDescription("Lonice ko Sundjer Bob");
            articleClothes2.setCreationDate(new Date());
            articleClothes2.setPrice(75.0);
            articleClothes2.setSex(Sex.MEN);
            articleClothes2.setSubtype(SubtypeClothes.JEANS);
            articleClothes2.setSize("L");
            clothesRepository.save(articleClothes2);
        }
    }
}
