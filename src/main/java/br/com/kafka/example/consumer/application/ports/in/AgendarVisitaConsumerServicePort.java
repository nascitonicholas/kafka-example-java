package br.com.kafka.example.consumer.application.ports.in;

import br.com.kafka.example.consumer.application.core.domain.Visita;

public interface AgendarVisitaConsumerServicePort {

    Visita agendarVisitaConsumer(Visita visita);

}
