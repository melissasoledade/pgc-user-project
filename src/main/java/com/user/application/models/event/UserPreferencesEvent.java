package com.user.application.models.event;

import lombok.Builder;
import lombok.Data;

import java.util.TimeZone;

@Data
@Builder
public class UserPreferencesEvent {

    private Boolean whatsAppOptIn;
    private Boolean emailOptIn;
    private Boolean phoneOptIn;
    private Boolean whatsAppPromotional;
    private Boolean emailPromotional;
    private Boolean pushNotification;
    private String language;
    private TimeZone timeZone;
}
