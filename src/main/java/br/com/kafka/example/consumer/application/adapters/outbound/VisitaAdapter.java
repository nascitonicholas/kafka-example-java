package br.com.kafka.example.consumer.application.adapters.outbound;

import br.com.kafka.example.consumer.application.adapters.outbound.entity.VisitaEntity;
import br.com.kafka.example.consumer.application.adapters.outbound.mapper.VisitaMapper;
import br.com.kafka.example.consumer.application.adapters.outbound.repository.VisitaRepository;
import br.com.kafka.example.consumer.application.core.domain.Visita;
import br.com.kafka.example.consumer.application.ports.out.VisitaConsumerPortOut;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VisitaAdapter implements VisitaConsumerPortOut {

    private final VisitaRepository visitaRepository;
    private final VisitaMapper mapper = Mappers.getMapper(VisitaMapper.class);

    @Override
    @Transactional
    public Visita agendarVisita(Visita visita) {
        VisitaEntity visitaAgendada = mapper.mapToVisitaEntity(visita);
        visitaRepository.save(visitaAgendada);
        return mapper.mapToVisita(visitaAgendada);
    }

    @Override
    @Transactional
    public Visita getVisitaPorData(String data, String horario) {
        Optional<VisitaEntity> visitaEntity = visitaRepository.getByDataAndHorario(data, horario);
        if(visitaEntity.isPresent())  {
            Visita visita = mapper.mapToVisita(visitaEntity.get());
            return visita;
        }
        return new Visita();
    }

}
