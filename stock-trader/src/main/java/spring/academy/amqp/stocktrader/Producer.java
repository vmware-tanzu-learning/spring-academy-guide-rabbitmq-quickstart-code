package spring.academy.amqp.stocktrader;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class Producer {

    private final String EXCHANGE = "STOCK";
    private String[] routingKeys = new String[]{"market.us","market.eu","market.jp","market.gb"};

    private Random random = new Random();
    private AmqpTemplate amqpTemplate;

    public Producer(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void marketPublisher(){
        String routingKey = routingKeys[random.nextInt(routingKeys.length)];
        String stockValue = new DecimalFormat("#.##").format(-10.0 + random.nextDouble() * 20.0);

        this.amqpTemplate.convertAndSend(EXCHANGE, routingKey, stockValue);
    }

}
