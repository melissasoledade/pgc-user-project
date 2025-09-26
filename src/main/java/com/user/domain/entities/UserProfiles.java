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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "`user_profiles`")
public class UserProfiles {

    @Id
    @Column(nullable = false, unique = true, name = "user_profiles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProfilesId;

    @Column(nullable = false, name = "profile_code")
    private Long profileCode;

    @Column(nullable = false, name = "profile_name")
    private String profileName;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
