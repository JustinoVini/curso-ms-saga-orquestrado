package br.com.microservices.orchestrated.productvalidationservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    /*AllArgs, vai injetar tudo, até o start saga, Required injeta somente o kafka e ingora o startSaga*/
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.orchestrator}")
    private String orchestratorTopic;

    public void sendEvent(String payload) {
        try {
            log.info("Sending event to topic {} with data {}", orchestratorTopic, payload);
            /*Envia o dado*/
            kafkaTemplate.send(orchestratorTopic, payload);
        } catch (Exception e) {
            log.error("Error trying to send data to topic {} with data {}", orchestratorTopic, payload, e);
        }
    }

}
