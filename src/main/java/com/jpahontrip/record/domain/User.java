package com.jpahontrip.record.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter @Setter
public class User extends  BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider", nullable = false, length = 20)
    private String provider;

    @Column(name = "social_id", nullable = false, length = 255)
    private String socialId;

    @Column(name = "nickname", length = 255)
    private String nickname;

    @Column(name = "profile_image", length = 1000)
    private String profileImage;

    @Column(name = "gender")
    private Integer gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_range")
    private AgeRangeType ageRangeType;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "access_token", length = 255)
    private String accessToken;

    @Column(name = "expires_at")
    private Timestamp expiresAt;

    @Column(name = "refresh_token", length = 255)
    private String refreshToken;

    @Column(name = "refresh_token_expires_at")
    private Timestamp refreshTokenExpiresAt;

    // Getters and setters
}

