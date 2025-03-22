package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.services.ArticleService;
import shoppingservice.services.ClothesService;
import shoppingservice.services.ShoesService;

@RestController()
@RequestMapping("/api/v1/men")
@AllArgsConstructor
public class ArticleMenController {
    private final ShoesService shoesService;
    private final ClothesService clothesService;
    private final ArticleService articleService;

    @GetMapping(path = "/article/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "check in both repositories for article")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(articleService.getArticle(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/article/{id} went wrong: " + e.getMessage());
        }
    }

    @GetMapping(path = "/clothes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "returns one clothes article")
    public ResponseEntity<?> getArticleById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clothesService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/clothes/{id} went wrong: " + e.getMessage());
        }
    }

    @GetMapping(path = "/clothes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "returns all clothes articles by subtype")
    public ResponseEntity<?> getAllClothesBySubtype(@RequestParam String subtype) {
        try {
            return ResponseEntity.ok(clothesService.findBySubtype(subtype));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/men/clothes went wrong");
        }
    }

    @GetMapping("/shoes/{id}")
    @Operation( summary = "returns one shoes article")
    public ResponseEntity<?> getShoesById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(shoesService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/shoes/{id} went wrong");
        }
    }

    @GetMapping("/shoes")
    @Operation(summary = "returns all shoes articles by subtype")
    public ResponseEntity<?> getAllShoesBySubtype(@RequestParam String subtype) {
        try {
            return ResponseEntity.ok(shoesService.findBySubtype(subtype));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/men/shoes went wrong");
        }
    }

    @GetMapping("/discount")
    @Operation(summary = "all articles on discount")
    public ResponseEntity<?> getAllDiscount() {
        try {

            return ResponseEntity.ok(articleService.findAllOnDiscount());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("/men/discount went wrong");
        }
    }

    @GetMapping("/trending")
    @Operation(summary = "top 10 most visited clothes")
    public ResponseEntity<?> getTop10MostVisitedClothes() {
        try {
            return ResponseEntity.ok(articleService.mostVisited());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/men/trending went wrong");
        }
    }

    @GetMapping("/newest")
    @Operation(summary = "returns 10 newest articles")
    public ResponseEntity<?> getNewestArticles() {
        try {
            return ResponseEntity.ok(articleService.findAllNewest());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/men/newest went wrong");
        }
    }
}
