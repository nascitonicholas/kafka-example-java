package br.com.kafka.example.producer.application.adapters.inbound.mapper;

import br.com.kafka.example.producer.application.adapters.inbound.request.AgendarVisitaRequest;
import br.com.kafka.example.producer.application.core.domain.Visita;
import org.mapstruct.Mapper;

@Mapper
public interface AgendarVisitaMapper {

    Visita mapToVisita(AgendarVisitaRequest visita);

}
