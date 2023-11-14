package ru.skillbox.demo.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name="subscriptions")
@IdClass(SubscriptionId.class)
@FilterDef(name = "userIdFilter", parameters = @ParamDef(name = "userId", type = "long"))
@Filter(name = "userIdFilter", condition = "user_id = :userId")
public class Subscription {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Id
    @Column(name="subscribed_to_user_id")
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
