package com.mock.ncb.controller;

import com.mock.ncb.dto.ResponseKafkaDto;
import com.mock.ncb.event.LedgerAccountCreatedEvent;
import com.mock.ncb.event.UserActivatedEvent;
import com.mock.ncb.provider.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class NCBController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/ledger")
    public ResponseEntity<ResponseKafkaDto> createLedger(@RequestBody LedgerAccountCreatedEvent event) {
        kafkaProducer.publishLedgerEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }

    @PostMapping("/leapx")
    public ResponseEntity<ResponseKafkaDto> createLeapx(@RequestBody UserActivatedEvent event) {
        kafkaProducer.publishLeapxEvent(event);
        return ResponseEntity.ok().body(ResponseKafkaDto.builder().status(200).build());
    }
}
