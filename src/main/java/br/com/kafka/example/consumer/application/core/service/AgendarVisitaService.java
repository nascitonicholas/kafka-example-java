package br.com.kafka.example.consumer.application.core.service;

import br.com.kafka.example.consumer.application.core.domain.Visita;
import br.com.kafka.example.consumer.application.ports.in.AgendarVisitaServicePort;
import br.com.kafka.example.consumer.application.ports.out.VisitaPortOut;

public class AgendarVisitaService implements AgendarVisitaServicePort {

    private final VisitaPortOut agendarVisitaPort;

    public AgendarVisitaService(VisitaPortOut agendarVisitaPort) {
        this.agendarVisitaPort = agendarVisitaPort;
    }

    @Override
    public Visita agendarVisita(Visita visita) {
        try {
            Visita horarioDisponivel = agendarVisitaPort.getVisitaPorData(visita.getData());
            if(horarioDisponivel.getId() == null) {
                return agendarVisitaPort.agendarVisita(visita);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Visita();
    }

}
