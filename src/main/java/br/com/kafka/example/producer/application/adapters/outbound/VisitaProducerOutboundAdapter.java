package br.com.kafka.example.producer.application.adapters.outbound;

import br.com.kafka.example.producer.application.adapters.outbound.mapper.VisitaMapper;
import br.com.kafka.example.producer.application.core.domain.Visita;
import br.com.kafka.example.producer.application.ports.out.VisitaPortOut;
import br.com.kafka.example.producer.config.KafkaProducerConfig;
import br.com.kafka.example.protobuf.VisitaProto;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class VisitaProducerOutboundAdapter implements VisitaPortOut {

    private String topic;
    private final KafkaTemplate<String, VisitaProto.VisitaProtoMessage> kafkaTemplate;
    private final VisitaMapper mapper = Mappers.getMapper(VisitaMapper.class);

    public VisitaProducerOutboundAdapter(@Value("${topic.name}") String topic,
                                         KafkaTemplate<String, VisitaProto.VisitaProtoMessage> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

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

            kafkaTemplate.send(new ProducerRecord<String, VisitaProto.VisitaProtoMessage>(topic, "Credit Card", visitaMessage));
            return visita;
         } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Visita();
        }
    }

}
