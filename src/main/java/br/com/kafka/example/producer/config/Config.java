package br.com.kafka.example.producer.config;

import br.com.kafka.example.producer.application.core.useCase.AgendarVisitaProducerService;
import br.com.kafka.example.producer.application.ports.out.VisitaPortOut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public AgendarVisitaProducerService agendarVisita(VisitaPortOut visitaPortOut) {
        return new AgendarVisitaProducerService(visitaPortOut);
    }

}
