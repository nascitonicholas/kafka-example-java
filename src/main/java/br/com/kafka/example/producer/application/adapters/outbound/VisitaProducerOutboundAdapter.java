package br.com.kafka.example.producer.application.adapters.outbound;

import br.com.kafka.example.producer.application.adapters.outbound.mapper.VisitaMapper;
import br.com.kafka.example.producer.application.core.domain.Visita;
import br.com.kafka.example.producer.application.ports.out.VisitaPortOut;
import br.com.kafka.example.producer.config.KafkaProducerConfig;
import br.com.kafka.example.protobuf.VisitaProto;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class VisitaProducerOutboundAdapter implements VisitaPortOut {

    private KafkaProducerConfig config;
    private VisitaMapper mapper = Mappers.getMapper(VisitaMapper.class);

    @Override
    @Transactional
    public Visita agendarVisita(Visita visita) {
        try {
            VisitaProto.VisitaProtoMessage.Usuario usuario = VisitaProto.VisitaProtoMessage.Usuario.newBuilder()
                            .setCpf(visita.getUsuario().getCpf())
                            .setNome(visita.getUsuario().getNome())
                            .setEmail(visita.getUsuario().getEmail())
                            .build();
            VisitaProto.VisitaProtoMessage visitaMessage = VisitaProto.VisitaProtoMessage.newBuilder()
                    .setData(visita.getData())
                    .setHorario(visita.getHorario())
                    .setUsuario(usuario)
                    .build();
            Producer<String, VisitaProto.VisitaProtoMessage> producer = config.VisitasProducerFactory();
            ProducerRecord<String, VisitaProto.VisitaProtoMessage> record = new ProducerRecord<>(config.createTopic().toString(), null, visitaMessage);
            producer.send(record);
            return visita;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Visita();
        }
    }

}
