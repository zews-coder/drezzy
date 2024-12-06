package userservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userservice.services.UserService;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAllUsers() {
        try{
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception e){
            return badRequest("/getAllUsers");
        }
    }

    private ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body("failed in: " + message);
    }

}
