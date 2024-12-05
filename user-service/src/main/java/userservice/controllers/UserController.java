package userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userservice.services.UserService;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("/api/v1/user/getAll failed");
        }
    }
}
