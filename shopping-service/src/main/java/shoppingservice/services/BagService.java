package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Bag;
import shoppingservice.repositories.BagRepository;
import shoppingservice.utils.dtos.CustomerArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BagService {
    private ArticleService articleService;
    private BagRepository bagRepository;

    public List<CustomerArticleDto> getAllBagArticles(Long userId) {
        List<CustomerArticleDto> customerArticleDtos = new ArrayList<>();
        if (bagRepository.findByUserId(userId).isPresent()){
            Set<String> bagArticles = bagRepository.findByUserId(userId).get().getArticleIds();
            for (String articleId : bagArticles) {
                customerArticleDtos.add(articleService.getArticle(Long.valueOf(articleId)));
            }
        }else {
            Bag bag = new Bag();
            bag.setUserId(userId);
            bagRepository.save(bag);
        }

        return customerArticleDtos;
    }

    public void addBagArticle(Long userId, String articleId) {
        if (bagRepository.findByUserId(userId).isPresent()){
            Bag bag = bagRepository.findByUserId(userId).get();
            bag.getArticleIds().add(articleId);
            bagRepository.save(bag);
        }else {
            Bag bag = new Bag();
            bag.setUserId(userId);
            bag.getArticleIds().add(articleId);
            bagRepository.save(bag);
        }
    }

    public void removeBagArticle(Long userId, String articleId) {
        if (bagRepository.findByUserId(userId).isPresent()){
            Bag bag = bagRepository.findByUserId(userId).get();
            bag.getArticleIds().remove(articleId);
            bagRepository.save(bag);
        }
    }
}
