package com.user.application.services.sns;

import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SnsNotificationService {

    private final SnsTemplate template;

    public SnsNotificationService(SnsTemplate template) {
        this.template = template;
    }

    public void publishMessage(String topicName, String message) {
        template.sendNotification(topicName, message);
    }
}
