package com.kafkaavroproducerconsumer.consumer;

import com.kafka.avro.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(
            topics = "${topic.name.consumer}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, Student> consumerRecord){
        log.info("**** avro started reading topic:{} partition:{} offset:{}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        String key = consumerRecord.key();
        Student value = consumerRecord.value();
        log.info("Data consumed key: {} value: {}", key, value.toString());
    }
}
