package com.user.application.services.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.application.dto.event.UserEvent;
import com.user.fixtures.application.event.UserEventHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserEventPublisherIT {

    @Value("${spring.cloud.aws.sns.topic.user}")
    private String snsTopicName;

    @Autowired
    private UserEventPublisher publisher;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SnsNotificationService snsNotificationService;

    @Test
    void shouldPublishMessageUsingRealSpringContext() throws Exception {
        // given
        final UserEvent event = UserEventHelper.defaultUserEvent().build();

        // when
        publisher.publishMessage(event);

        // then
        final String expectedPayload = objectMapper.writeValueAsString(event);

        verify(snsNotificationService)
                .publishMessage(snsTopicName, expectedPayload);
    }

    @Test
    void shouldHandleExceptionAndNotThrow() throws Exception {
        // given
        final UserEvent event = UserEventHelper.defaultUserEvent().build();

        // mock serialization failure
        doThrow(new RuntimeException("SNS error"))
                .when(snsNotificationService)
                .publishMessage(anyString(), anyString());

        // when + then
        assertDoesNotThrow(() -> publisher.publishMessage(event));
    }
}
