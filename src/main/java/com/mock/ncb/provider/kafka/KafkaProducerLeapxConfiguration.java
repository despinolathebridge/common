package com.mock.ncb.provider.kafka;

import io.cloudevents.CloudEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@Component
public class KafkaProducerLeapxConfiguration {

    @Value("${spring.leapx-kafka.bootstrap-servers}")
    private String LEAPX_BOOTSRAP_SERVER;
    @Value("${spring.leapx-kafka.client-id}")
    private String LEAPX_CLIENT_ID;
    @Value("${spring.leapx-kafka.consumer.group-id}")
    private String LEAPX_CONSUMER_GROUP_ID;
    @Value("${spring.leapx-kafka.sasl.security-protocol}")
    private String LEAPX_SECURITY_PROTOCOL;
    @Value("${spring.leapx-kafka.sasl.mechanism}")
    private String LEAPX_SASL_MECHANISM;
    @Value("${spring.leapx-kafka.sasl.jaas-config}")
    private String LEAPX_SASL_JAAS_CONFIG;

    private KafkaTemplate<String, CloudEvent> kafkaTemplateLeapx;

    private ProducerFactory<String, CloudEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, LEAPX_BOOTSRAP_SERVER);
        configProps.put(ConsumerConfig.CLIENT_ID_CONFIG, LEAPX_CLIENT_ID);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, LEAPX_CONSUMER_GROUP_ID);
        configProps.put("key.serializer", org.apache.kafka.common.serialization.StringSerializer.class);
        configProps.put("value.serializer", io.cloudevents.kafka.CloudEventSerializer.class);
        configProps.put("security.protocol", LEAPX_SECURITY_PROTOCOL);
        configProps.put("sasl.mechanism", LEAPX_SASL_MECHANISM);
        configProps.put("sasl.jaas.config", LEAPX_SASL_JAAS_CONFIG);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private KafkaTemplate<String, CloudEvent> createKafkaTemplateLeapx() {
        return new KafkaTemplate<>(producerFactory());
    }

    public KafkaTemplate<String, CloudEvent> getKafkaTemplateLeapx(){
        if(Objects.isNull(kafkaTemplateLeapx)){
            kafkaTemplateLeapx = createKafkaTemplateLeapx();
        }
        return this.kafkaTemplateLeapx;
    }
}
