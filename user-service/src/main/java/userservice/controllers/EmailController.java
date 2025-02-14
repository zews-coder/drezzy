package userservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userservice.services.EmailService;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<?> sendEmail() {
        try {
            emailService.sendSimpleMessage("jane06.ristic@gmail.com", "testing", "welcome to the drezzy club");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/attachment")
    public ResponseEntity<?> sendAttachment() {
        try {
            emailService.sendMailWithAttachment("jane06.ristic@gmail.com", "testing attachments", "can you see attachments?");
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
