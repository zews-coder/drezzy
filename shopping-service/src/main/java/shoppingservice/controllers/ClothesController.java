package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.services.ClothesService;
import shoppingservice.services.JwtService;
import shoppingservice.utils.dtos.CreateClothesDto;

@RestController
@RequestMapping("/api/v1/clothes")
@AllArgsConstructor
public class ClothesController {
    private final JwtService jwtService;
    private final ClothesService clothesService;

    @GetMapping()
    public ResponseEntity<?> getAllClothes(@RequestHeader("Authorization") String token) {
        try{
            return null;
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    @Operation(summary = "creating new clothes")
    public ResponseEntity<?> addClothes(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CreateClothesDto createClothesDto) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(clothesService.addClothes(createClothesDto));
            }
            return ResponseEntity.status(403).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("/clothes/add went wrong");
        }
    }
}
