package br.com.kafka.example.consumer.config;

import br.com.kafka.example.consumer.application.core.service.AgendarVisitaConsumerService;
import br.com.kafka.example.consumer.application.ports.out.VisitaConsumerPortOut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfigBeans {

    @Bean
    public AgendarVisitaConsumerService agendarVisitaConsumer(VisitaConsumerPortOut visitaConsumerPortOut) {
        return new AgendarVisitaConsumerService(visitaConsumerPortOut);
    }

}
