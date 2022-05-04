package br.com.kafka.example.consumer.application.adapters.outbound.repository;

import br.com.kafka.example.consumer.application.adapters.outbound.entity.VisitaEntity;
import br.com.kafka.example.consumer.application.core.domain.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitaRepository extends JpaRepository<VisitaEntity, Long> {

    Optional<Visita> getByData(String data);

}
