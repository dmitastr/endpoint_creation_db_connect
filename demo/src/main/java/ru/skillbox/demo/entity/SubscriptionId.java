package ru.skillbox.demo.entity;

import java.io.Serializable;


public class SubscriptionId implements Serializable {

    private Long userId;

    public SubscriptionId(Long userId, Long subscribedToUserId) {
        this.userId = userId;
        this.subscribedToUserId = subscribedToUserId;
    }

    public SubscriptionId() {
    }

    private Long subscribedToUserId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubscribedToUserId() {
        return subscribedToUserId;
    }

    public void setSubscribedToUserId(Long subscribedToUserId) {
        this.subscribedToUserId = subscribedToUserId;
    }
}
