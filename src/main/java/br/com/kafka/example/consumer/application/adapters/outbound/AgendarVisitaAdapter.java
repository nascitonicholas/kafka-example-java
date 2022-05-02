package br.com.kafka.example.consumer.application.adapters.outbound;

import br.com.kafka.example.consumer.application.adapters.outbound.entity.VisitaEntity;
import br.com.kafka.example.consumer.application.adapters.outbound.mapper.VisitaMapper;
import br.com.kafka.example.consumer.application.adapters.outbound.repository.VisitaRepository;
import br.com.kafka.example.consumer.application.core.domain.Visita;
import br.com.kafka.example.consumer.application.ports.out.AgendarVisitaPort;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class AgendarVisitaAdapter implements AgendarVisitaPort {

    private final VisitaRepository visitaRepository;
    private final VisitaMapper mapper = Mappers.getMapper(VisitaMapper.class);

    @Override
    @Transactional
    public Visita agendarVisita(Visita visita) {
        VisitaEntity visitaAgendada = mapper.mapToVisitaEntity(visita);
        visitaRepository.save(visitaAgendada);
        return mapper.mapToVisita(visitaAgendada);
    }
}
