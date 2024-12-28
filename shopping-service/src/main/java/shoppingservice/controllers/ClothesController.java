package shoppingservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import shoppingservice.services.ClothesService;

@RestController("/api/v1")
@AllArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;

    @GetMapping()
    public ResponseEntity<?> getAllClothes(@RequestHeader("Authorization") String token) {
        try{
            return null;
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
