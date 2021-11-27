package com.example.backend.models.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secure_tokens")
public class SecureToken extends BaseEntity{

    private String token;
    private Timestamp timestamp;
    private LocalDateTime expireAt;
    private User user;
    private boolean isExpired;

    public SecureToken() {
    }

    @Column(unique = true)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(updatable = false)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Column(updatable = false)
    @Basic(optional = false)
    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now());
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
