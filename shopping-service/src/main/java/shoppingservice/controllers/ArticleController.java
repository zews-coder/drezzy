package shoppingservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppingservice.services.ArticleService;
import shoppingservice.services.JwtService;

@RestController
@RequestMapping("/api/v1/articles")
@AllArgsConstructor
public class ArticleController{
    private final ArticleService articleService;
    private final JwtService jwtService;

    @GetMapping(path = "/getAllArticlesFromVendor")
    public ResponseEntity<?> getAllArticlesFromVendor(@RequestHeader("Authorization") String authorizationHeader){
        try{
            String token = authorizationHeader.substring(7);
            Long vendorId = jwtService.extractId(token);
            return ResponseEntity.ok(articleService.getAllArticlesByVendor(vendorId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("/getAllArticlesFromVendor went wrong");
        }
    }
}
