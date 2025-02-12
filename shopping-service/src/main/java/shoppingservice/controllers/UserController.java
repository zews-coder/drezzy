package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.services.BagService;
import shoppingservice.services.JwtService;
import shoppingservice.services.WishlistService;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final JwtService jwtService;
    private final WishlistService wishlistService;
    private final BagService bagService;

    @GetMapping(path = "/wishlist",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "returns all items on wishlist of one user")
    public ResponseEntity<?> getWishlist(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            return ResponseEntity.ok(wishlistService.getAllWishlistArticles(jwtService.extractId(authorizationHeader.substring(7))));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("GET /wishlist failed "+ e.getMessage());
        }
    }

    @GetMapping(path = "/bag",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "returns all items in bag of one user")
    public ResponseEntity<?> getBag(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            return ResponseEntity.ok(bagService.getAllBagArticles(jwtService.extractId(authorizationHeader.substring(7))));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("GET /bag failed "+ e.getMessage());
        }
    }

    @PostMapping(path = "/wishlist/{articleId}")
    @Operation(description = "add new article on wishlist")
    public ResponseEntity<?> addWishlist(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String articleId) {
        try {
            wishlistService.addWishlistArticle(jwtService.extractId(authorizationHeader.substring(7)), articleId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("POST /wishlist/{articleID} failed "+ e.getMessage());
        }
    }

    @PostMapping(path = "/bag/{articleId}")
    @Operation(description = "add new article in bag")
    public ResponseEntity<?> addBag(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String articleId) {
        try {
            bagService.addBagArticle(jwtService.extractId(authorizationHeader.substring(7)), articleId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("POST /bag/{articleID} failed "+ e.getMessage());
        }
    }

    @DeleteMapping(path = "/wishlist/{articleId}")
    @Operation(description = "remove article from wishlist")
    public ResponseEntity<?> removeWishlist(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String articleId) {
        try {
            wishlistService.removeWishlistArticle(jwtService.extractId(authorizationHeader.substring(7)), articleId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("DELETE /wishlist/{articleID} failed "+ e.getMessage());
        }
    }

    @DeleteMapping(path = "/bag/{articleId}")
    @Operation(description = "remove article from bag")
    public ResponseEntity<?> removeBag(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String articleId) {
        try {
            bagService.removeBagArticle(jwtService.extractId(authorizationHeader.substring(7)), articleId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("DELETE /bag/{articleID} failed "+ e.getMessage());
        }
    }
}
