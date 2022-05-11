package br.com.kafka.example.consumer.application.adapters.inbound;

import br.com.kafka.example.consumer.application.core.domain.Usuario;
import br.com.kafka.example.consumer.application.core.domain.Visita;
import br.com.kafka.example.consumer.application.ports.in.AgendarVisitaConsumerServicePort;
import br.com.kafka.example.protobuf.VisitaProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class VisitaConsumer {

    @Autowired
    private AgendarVisitaConsumerServicePort agendarVisitaServicePort;

    @KafkaListener(topics = "${topic.name}")
    public void consumer(ConsumerRecord<String, VisitaProto.VisitaProtoMessage> record, Acknowledgment ack) {
        try {
            log.info("Iniciando consumo da mensagem: particao -> {} topico -> {}", record.partition(), record.topic());
            VisitaProto.VisitaProtoMessage message = record.value();
            Visita visitaAgendada = new Visita();
            visitaAgendada.setId(record.timestamp());
            visitaAgendada.setData(message.getData());
            visitaAgendada.setHorario(message.getHorario());
            Usuario usuario = new Usuario();
            usuario.setCpf(message.getUsuario().getCpf());
            usuario.setNome(message.getUsuario().getNome());
            usuario.setEmail(message.getUsuario().getEmail());
            visitaAgendada.setUsuario(usuario);
            Visita visita = agendarVisitaServicePort.agendarVisitaConsumer(visitaAgendada);
            log.info("Visita agendada com sucesso, dados: {}", visita);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao consumir mensagem do tópico: {}", e.getMessage());
        }
    }

}
