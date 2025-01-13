package shoppingservice.configs;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shoppingservice.enitites.ArticleClothes;
import shoppingservice.enitites.Bill;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;
import shoppingservice.repositories.BillRepository;
import shoppingservice.repositories.ClothesRepository;

import java.util.Date;

@Component
@AllArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final ClothesRepository clothesRepository;
    private final BillRepository billRepository;

    @Override
    public void run(String... args) throws Exception {
        clothesRepository.deleteAll();

        Bill bill = new Bill();
        bill.setDate(new Date());
        bill.setCustomerId(15L);
        billRepository.save(bill);

        if (clothesRepository.count() == 0) {
            ArticleClothes articleClothes = new ArticleClothes();
            articleClothes.setVendorId(2L);
            articleClothes.setTitle("Majcica");
            articleClothes.setDescription("Preljepa majcica po povoljnoj ceni");
            articleClothes.setCreationDate(new Date());
            articleClothes.setPrice(50.0);
            articleClothes.setSex(Sex.MEN);
            articleClothes.setSubtype(SubtypeClothes.SHIRTS);
            articleClothes.setSize("M");

            ArticleClothes articleClothes2 = new ArticleClothes();
            articleClothes2.setVendorId(2L);
            articleClothes2.setTitle("Lone");
            articleClothes2.setDescription("Lonice ko Sundjer Bob");
            articleClothes2.setCreationDate(new Date());
            articleClothes2.setPrice(75.0);
            articleClothes2.setSex(Sex.MEN);
            articleClothes2.setSubtype(SubtypeClothes.JEANS);
            articleClothes2.setSize("L");

            clothesRepository.save(articleClothes);
            clothesRepository.save(articleClothes2);
        }
    }
}
