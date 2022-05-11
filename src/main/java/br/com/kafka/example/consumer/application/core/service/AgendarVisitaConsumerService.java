package br.com.kafka.example.consumer.application.core.service;

import br.com.kafka.example.consumer.application.core.domain.Visita;
import br.com.kafka.example.consumer.application.ports.in.AgendarVisitaConsumerServicePort;
import br.com.kafka.example.consumer.application.ports.out.VisitaConsumerPortOut;

public class AgendarVisitaConsumerService implements AgendarVisitaConsumerServicePort {

    private final VisitaConsumerPortOut agendarVisitaPort;

    public AgendarVisitaConsumerService(VisitaConsumerPortOut agendarVisitaPort) {
        this.agendarVisitaPort = agendarVisitaPort;
    }

    @Override
    public Visita agendarVisitaConsumer(Visita visita) {
        Visita horarioDisponivel = agendarVisitaPort.getVisitaPorData(visita.getData(), visita.getHorario());
        Visita visitaAgendada = null;
        if(horarioDisponivel != null) {
            if(horarioDisponivel.getId() == null) {
                visitaAgendada = agendarVisitaPort.agendarVisita(visita);
            } else {
                throw new RuntimeException("Horário reservado");
            }
        }
        return visitaAgendada;
    }

}
