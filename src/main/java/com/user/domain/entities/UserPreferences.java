package com.user.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "`user_preferences`")
public class UserPreferences {

    @Id
    @Column(nullable = false, unique = true, name = "user_preferences_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPreferencesId;

    @Column(name = "whats_app_opt_in")
    private Boolean whatsAppOptIn;

    @Column(name = "email_opt_in")
    private Boolean emailOptIn;

    @Column(name = "phone_opt_in")
    private Boolean phoneOptIn;

    @Column(name = "whats_app_promotional")
    private Boolean whatsAppPromotional;

    @Column(name = "email_promotional")
    private Boolean emailPromotional;

    @Column(name = "push_notification")
    private Boolean pushNotification;

    @Column
    private String language;

    @Column
    private TimeZone timeZone;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
