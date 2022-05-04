package br.com.kafka.example.producer.application.ports.in;

import br.com.kafka.example.producer.application.core.domain.Visita;

public interface AgendarVisitaServicePort {

    Visita agendarVisita(Visita visita);

}
