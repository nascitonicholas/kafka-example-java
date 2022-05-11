package br.com.kafka.example.consumer.application.adapters.outbound.mapper;

import br.com.kafka.example.consumer.application.adapters.outbound.entity.VisitaEntity;
import br.com.kafka.example.consumer.application.core.domain.Visita;
import org.mapstruct.Mapper;

@Mapper
public interface VisitaMapper {

    Visita mapToVisita(VisitaEntity visita);
    VisitaEntity mapToVisitaEntity(Visita visita);
}
