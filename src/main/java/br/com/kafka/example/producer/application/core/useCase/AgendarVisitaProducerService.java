package br.com.kafka.example.producer.application.core.useCase;

import br.com.kafka.example.producer.application.core.domain.Visita;
import br.com.kafka.example.producer.application.ports.in.AgendarVisitaServicePort;
import br.com.kafka.example.producer.application.ports.out.VisitaPortOut;

public class AgendarVisitaProducerService implements AgendarVisitaServicePort {

    private final VisitaPortOut agendarVisitaPort;

    public AgendarVisitaProducerService(VisitaPortOut agendarVisitaPort) {
        this.agendarVisitaPort = agendarVisitaPort;
    }

    @Override
    public Visita agendarVisita(Visita visita) {
        return agendarVisitaPort.agendarVisita(visita);
    }

}
