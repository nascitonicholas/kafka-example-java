package br.com.kafka.example.consumer.application.core.service;

import br.com.kafka.example.consumer.application.core.domain.Visita;
import br.com.kafka.example.consumer.application.ports.in.AgendarVisitaServicePort;
import br.com.kafka.example.consumer.application.ports.out.AgendarVisitaPort;

public class AgendarVisitaService implements AgendarVisitaServicePort {

    private final AgendarVisitaPort agendarVisitaPort;

    public AgendarVisitaService(AgendarVisitaPort agendarVisitaPort) {
        this.agendarVisitaPort = agendarVisitaPort;
    }

//    Aqui deve ser aplicada as regras de negócio (ex: validar se a data e hora da visita estão disponiveis)
//    no caso, desejo apenas salvar no banco
    @Override
    public Visita agendarVisita(Visita visita) {
        return agendarVisitaPort.agendarVisita(visita);
    }
}
