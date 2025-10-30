package com.user.application.services.sns;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserNotificationService {

    private final SnsNotificationService snsNotificationService;

    @Value("${spring.cloud.aws.sns.topic.user}")
    private String snsTopicName;

    private void publish(String message) {
        this.snsNotificationService.publishMessage(snsTopicName, message);
    }

    public void publishMessage(String message) {
        try {
            this.publish(message);
        } catch (Exception e) {
            log.info("Could not send message to SNS. Topic: {} Message: {} Error: {}",
                    snsTopicName, message, e.getMessage(), e);
        }
    }
}
