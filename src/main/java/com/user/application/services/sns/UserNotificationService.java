package com.user.application.services.sns;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
public class UserNotificationService {

    private final SnsNotificationService snsNotificationService;

    @Value("${cloud.aws.sns.topic.user}")
    private String snsTopicName;

    public void publish(String message) {
        this.snsNotificationService.publishMessage(snsTopicName, message);
    }
}
