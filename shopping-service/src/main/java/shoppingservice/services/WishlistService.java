package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Wishlist;
import shoppingservice.repositories.WishlistRepository;
import shoppingservice.utils.dtos.CustomerArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class WishlistService {
    private ArticleService articleService;
    private WishlistRepository wishlistRepository;

    public List<CustomerArticleDto> getAllWishlistArticles(Long userId) {
        List<CustomerArticleDto> articlesWithImageDtos = new ArrayList<>();
        if (wishlistRepository.findByUserId(userId).isPresent()) {
            Set<String> wishlistArticles = wishlistRepository.findByUserId(userId).get().getArticleIds();
            for (String articleId : wishlistArticles) {
                articlesWithImageDtos.add(articleService.getArticle(Long.valueOf(articleId)));
            }
        }else {
            Wishlist wishlist = new Wishlist();
            wishlist.setUserId(userId);
            wishlistRepository.save(wishlist);
        }

        return articlesWithImageDtos;
    }

    public void addWishlistArticle(Long userId, String articleId) {
        if (wishlistRepository.findByUserId(userId).isPresent()) {
            Wishlist wishlist = wishlistRepository.findByUserId(userId).get();
            wishlist.getArticleIds().add(articleId);
            wishlistRepository.save(wishlist);
        } else {
            Wishlist newWishlist = new Wishlist();
            newWishlist.setUserId(userId);
            newWishlist.getArticleIds().add(articleId);
            wishlistRepository.save(newWishlist);
        }
    }

    public void removeWishlistArticle(Long userId, String articleId) {
        if (wishlistRepository.findByUserId(userId).isPresent()) {
            Wishlist wishlist = wishlistRepository.findByUserId(userId).get();
            wishlist.getArticleIds().remove(articleId);
            wishlistRepository.save(wishlist);
        }
    }
}
