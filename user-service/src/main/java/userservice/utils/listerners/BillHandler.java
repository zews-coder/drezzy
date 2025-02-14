package userservice.utils.listerners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import userservice.utils.dtos.BillDto;

@Component
public class BillHandler {

    @RabbitListener(queues = "billQueue")
    public void receiveMessage(BillDto billDto) {
        System.out.println("MQ: " + billDto.toString());
    }

    //testiranje
//    @RabbitListener(queues = "billQueue")
//    public void receiveMessage(String message) {
//        System.out.println("MQ: " + message);
//    }
}
