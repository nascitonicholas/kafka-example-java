package br.com.kafka.example.producer.application.adapters.inbound;

import br.com.kafka.example.producer.application.adapters.inbound.mapper.AgendarVisitaMapper;
import br.com.kafka.example.producer.application.adapters.inbound.request.AgendarVisitaRequest;
import br.com.kafka.example.producer.application.core.domain.Visita;
import br.com.kafka.example.producer.application.ports.in.AgendarVisitaServicePort;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VisitaController {

    private final AgendarVisitaServicePort agendarVisitaPort;
    private final AgendarVisitaMapper mapper = Mappers.getMapper(AgendarVisitaMapper.class);

    @PostMapping("/agendar-visitas")
    public ResponseEntity<?> post(@RequestBody AgendarVisitaRequest visitaRequest) {
        Visita visitaAgendada = agendarVisitaPort.agendarVisita(mapper.mapToVisita(visitaRequest));
        return ResponseEntity.ok(visitaAgendada);
    }

}
