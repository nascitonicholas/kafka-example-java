package br.com.kafka.example.consumer.application.ports.out;

import br.com.kafka.example.consumer.application.core.domain.Visita;

public interface VisitaConsumerPortOut {

    Visita agendarVisita(Visita visita);
    Visita getVisitaPorData(String data, String horario);

}
