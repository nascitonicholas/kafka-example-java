package br.com.kafka.example.producer.config;

import br.com.kafka.example.protobuf.VisitaProto;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
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

    public static Properties visitasProducerProperties() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "http://localhost:9092");
        properties.setProperty("schema.registry.url", "http://localhost:8081");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaProtobufSerializer.class.getName());
        return properties;
    }

    public static KafkaProducer<String, VisitaProto.VisitaProtoMessage> kafkaProducer() {
        return new KafkaProducer<String, VisitaProto.VisitaProtoMessage>(visitasProducerProperties());
    }


}
