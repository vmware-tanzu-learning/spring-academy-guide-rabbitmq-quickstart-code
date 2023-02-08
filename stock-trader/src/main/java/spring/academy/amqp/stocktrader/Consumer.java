package spring.academy.amqp.stocktrader;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @RabbitListener(queues = "US")
    public void usConsumer(String in) {
        System.out.println(String.format("Market[US]: %s",in));
    }

    @RabbitListener(queues = "EU")
    public void euConsumer(String in) {
        System.out.println(String.format("Market[EU]: %s",in));
    }

}
