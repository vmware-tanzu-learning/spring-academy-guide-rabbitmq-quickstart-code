package spring.academy.amqp.stocktrader;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class StockTraderConfig {

    @Bean
    public TopicExchange stockExchange(){
        return new TopicExchange("STOCK",true,false);
    }

    @Bean
    public Binding stockUSBinding(TopicExchange stockExchange,Queue usQueue){
        return BindingBuilder.bind(usQueue).to(stockExchange).with("market.us");
    }

    @Bean
    public Binding stockEUBinding(TopicExchange stockExchange,Queue euQueue){
        return BindingBuilder.bind(euQueue).to(stockExchange).with("market.eu");
    }

    @Bean
    public Binding stockAuditBinding(TopicExchange stockExchange,Queue auditQueue){
        return BindingBuilder.bind(auditQueue).to(stockExchange).with("market.*");
    }

    @Bean
    public Queue usQueue() {
        return new Queue("US",true,false,false);
    }

    @Bean
    public Queue euQueue() {
        return new Queue("EU",true,false,false);
    }

    @Bean
    public Queue auditQueue() {
        return new Queue("AUDIT",true,false,false);
    }

}
