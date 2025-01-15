package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.services.JwtService;
import shoppingservice.services.ShoesService;
import shoppingservice.utils.dtos.CreateShoesDto;

@RestController
@RequestMapping("/api/v1/shoes")
@AllArgsConstructor
public class ShoesController {
    private final JwtService jwtService;
    private final ShoesService shoesService;

    @PostMapping("/add")
    @Operation(summary = "creating new shoes")
    public ResponseEntity<?> addShoes(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CreateShoesDto createShoesDto) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(shoesService.addShoes(createShoesDto));
            }
            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/shoes/add went wrong");
        }
    }
}
