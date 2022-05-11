package br.com.kafka.example.producer.config;

import br.com.kafka.example.protobuf.VisitaProto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaProperties properties;

    @Bean
    public NewTopic createTopic(@Value(value = "${topic.name}") String topic){
        return new NewTopic(topic, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, VisitaProto.VisitaProtoMessage> visitasProducerProperties() {
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, VisitaProto.VisitaProtoMessage> producer() {
        return new KafkaTemplate<>(visitasProducerProperties());
    }

}
