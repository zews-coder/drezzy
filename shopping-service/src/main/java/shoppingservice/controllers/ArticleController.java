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

    @GetMapping(path = "/getAllArticles")
    public ResponseEntity<?> getAllArticles(@RequestHeader("Authorization") String authorizationHeader){
        try{
            String role = jwtService.extractRole(authorizationHeader.substring(7));
            if (role.equalsIgnoreCase("admin")) {
                return ResponseEntity.ok(articleService.getAllArticles());
            }
            return ResponseEntity.badRequest().body("/getAllArticles went wrong");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("/getAllArticles went wrong");
        }
    }
}
