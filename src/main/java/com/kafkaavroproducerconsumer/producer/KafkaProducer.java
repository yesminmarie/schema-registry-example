package com.kafkaavroproducerconsumer.producer;

import com.kafka.avro.model.Student;
import com.kafkaavroproducerconsumer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaProducerService kafkaProducerService;

    @Value("${topic.name.producer}")
    private String avroTopicName;

    @PostMapping("/createStudent")
    public String sendDataToKafkaTopic(@RequestBody Student student){
        log.info("sending data to topic {} value: {}", avroTopicName, student.toString());
        kafkaProducerService.sendAvroData(avroTopicName, student);
        return "Data Posted";
    }
}
