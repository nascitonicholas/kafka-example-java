package br.com.kafka.example.producer.config;

import br.com.kafka.example.protobuf.VisitaProto;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {

    @Value(value = "${topic.name}")
    private String topic;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaAdress;

    @Value(value = "${spring.kafka.schema-registry}")
    private String schemaAdress;

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(topic, 3, (short) 1);
    }

    @Bean
    public KafkaProducer<String, VisitaProto.VisitaProtoMessage> VisitasProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAdress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializerConfig.class);
        configProps.put(KafkaProtobufSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaAdress);
        return new KafkaProducer<>(configProps);
    }

}
