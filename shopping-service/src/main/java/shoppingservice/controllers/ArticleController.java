package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.services.ArticleService;
import shoppingservice.services.JwtService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@AllArgsConstructor
public class ArticleController{
    private final ArticleService articleService;
    private final JwtService jwtService;

    @GetMapping(path = "/getAllArticles")
    @Operation(summary = "getting all articles in DB")
    public ResponseEntity<?> getAllArticles(){
        try{
//            @RequestHeader("Authorization") String authorizationHeader
//            String role = jwtService.extractRole(authorizationHeader.substring(7));
//            if (role.equalsIgnoreCase("admin")) {
                return ResponseEntity.ok(articleService.getAllArticles());
//            }
//            return ResponseEntity.badRequest().body("/getAllArticles went wrong");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("/getAllArticles went wrong" + e.getMessage());
        }
    }

}
