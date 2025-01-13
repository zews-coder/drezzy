package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Article;
import shoppingservice.repositories.ArticleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticlesByVendor(Long vendorId) {
        return this.articleRepository.findByVendorId(vendorId);
    }
}
