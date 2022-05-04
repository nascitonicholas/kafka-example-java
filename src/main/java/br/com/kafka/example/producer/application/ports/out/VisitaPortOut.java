package br.com.kafka.example.producer.application.ports.out;

import br.com.kafka.example.producer.application.core.domain.Visita;

public interface VisitaPortOut {

    Visita agendarVisita(Visita visita);

}
