package br.com.kafka.example.consumer.config;

import br.com.kafka.example.protobuf.VisitaProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, VisitaProto.VisitaProtoMessage> visitasConsumerProperties() {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

    @Bean
    public KafkaProperties.Listener listener() {
        return new KafkaProperties.Listener();
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, VisitaProto.VisitaProtoMessage>>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, VisitaProto.VisitaProtoMessage> listener =
                new ConcurrentKafkaListenerContainerFactory<>();

        listener.setConsumerFactory(visitasConsumerProperties());

        listener.getContainerProperties()
                .setMissingTopicsFatal(false);

        listener.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        listener.getContainerProperties().setSyncCommits(Boolean.TRUE);

        return listener;
    }

}
