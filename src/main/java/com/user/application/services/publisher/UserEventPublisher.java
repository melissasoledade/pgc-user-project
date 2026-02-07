package com.user.application.services.publisher;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.application.models.event.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserEventPublisher {

    private final SnsNotificationService snsNotificationService;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.aws.sns.topic.user}")
    private String snsTopicName;

    private void publish(UserEvent message) throws JsonProcessingException {
        final ObjectMapper nonNullMapper = objectMapper.copy();
        nonNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final String payload = nonNullMapper.writeValueAsString(message);
        this.snsNotificationService.publishMessage(snsTopicName, payload);
    }

    public void publishMessage(UserEvent message) {
        try {
            this.publish(message);
        } catch (Exception e) {
            log.info("Could not send message to SNS. Topic: {} Message: {} Error: {}",
                    snsTopicName, message, e.getMessage(), e);
        }
    }
}
