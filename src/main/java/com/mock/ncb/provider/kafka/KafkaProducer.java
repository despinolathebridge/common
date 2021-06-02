package com.mock.ncb.provider.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.ncb.event.LedgerAccountCreatedEvent;
import com.mock.ncb.event.UserActivatedEvent;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

@Slf4j
@Service
public class KafkaProducer {

	private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
	private final KafkaProducerLeapxConfiguration kafkaTemplateLeapx;
	private final ObjectMapper objectMapper;

	public KafkaProducer(KafkaTemplate<String, CloudEvent> kafkaTemplate, KafkaProducerLeapxConfiguration kafkaTemplateLeapx) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaTemplateLeapx = kafkaTemplateLeapx;
		this.objectMapper = new ObjectMapper();
	}

	public void publishLedgerEvent(LedgerAccountCreatedEvent event) {
		CloudEvent cloudEvent = CloudEventBuilder.v1()
				.withExtension("payloadversion", "0.0.1")
				.withId(UUID.randomUUID().toString())
				.withType("team.nautilus.event.accounts.ledger.new")
				.withSource(URI.create("dummy"))
				.withDataContentType("application/json")
				.withTime(Instant.now().atOffset(ZoneOffset.UTC))
				.withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
				.build();
        kafkaTemplate.send("ACCOUNTS", cloudEvent);
        log.info("Message published!");
    }

	public void publishLeapxEvent(UserActivatedEvent event) {
		CloudEvent cloudEvent = CloudEventBuilder.v1()
				.withExtension("payloadversion", "0.0.1")
				.withId(UUID.randomUUID().toString())
				.withType("team.nautilus.event.user.activated")
				.withSource(URI.create("dummy"))
				.withDataContentType("application/json")
				.withTime(Instant.now().atOffset(ZoneOffset.UTC))
				.withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
				.build();
		kafkaTemplateLeapx.getKafkaTemplateLeapx().send("USERS", cloudEvent);
		log.info("Message published!");
	}
}
