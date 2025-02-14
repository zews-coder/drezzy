package userservice.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import userservice.utils.dtos.BillDto;

import java.io.File;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jristic3620rn@raf.rs");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMailWithAttachment(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("jristic3620rn@raf.rs");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment("logo.png", new File("user-service/src/main/resources/files/drezzy.jpg"));
        helper.addAttachment("invoice.pdf", new File("user-service/src/main/resources/files/Janko Ristic CV_en.pdf"));

        emailSender.send(message);
    }


}
