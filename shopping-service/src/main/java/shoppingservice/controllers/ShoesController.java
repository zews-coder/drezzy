package shoppingservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shoppingservice.services.JwtService;
import shoppingservice.services.ShoesService;
import shoppingservice.utils.dtos.CreateClothesDto;
import shoppingservice.utils.dtos.CreateShoesDto;

@RestController
@RequestMapping("/api/v1/shoes")
@AllArgsConstructor
public class ShoesController {
    private final JwtService jwtService;
    private final ShoesService shoesService;

    @PostMapping(path = "/add", consumes = "multipart/form-data")
    @Operation(summary = "creating new shoes")
    public ResponseEntity<?> addShoes(@RequestHeader("Authorization") String authorizationHeader,
                                      @RequestPart("createShoesDto") CreateShoesDto createShoesDto,
                                      @RequestPart("image") MultipartFile image) {
        try {
            if (jwtService.isAdmin(authorizationHeader.substring(7))){
                return ResponseEntity.ok(shoesService.addShoes(createShoesDto, image));
            }
            return ResponseEntity.status(403).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("/shoes/add went wrong");
        }
    }
}
