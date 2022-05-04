package br.com.kafka.example.producer.config;

import br.com.kafka.example.producer.application.core.useCase.AgendarVisitaService;
import br.com.kafka.example.producer.application.ports.out.VisitaPortOut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public AgendarVisitaService agendarVisita(VisitaPortOut visitaPortOut) {
        return new AgendarVisitaService(visitaPortOut);
    }

}
