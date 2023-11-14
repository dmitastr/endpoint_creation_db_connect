package ru.skillbox.demo.service;

import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.entity.SubscriptionId;
import ru.skillbox.demo.repository.SubscriptionRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SubscriptionService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public boolean existsBySubscriptionId(long userId, long subscribedToUserId) {
        SubscriptionId subscriptionId = new SubscriptionId(userId, subscribedToUserId);
        return subscriptionRepository.existsById(subscriptionId);
    }

    public String createSubscription(Subscription subscription) {
        Subscription newSubscription = subscriptionRepository.save(subscription);
        return String.format("User ID=%s subscribed to user ID=%s", newSubscription.getUserId(), newSubscription.getSubscribedToUserId());
    }

    public List<Subscription> getUsersSubscriptions(Long userId) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("userIdFilter");
        filter.setParameter("userId", userId);
        List<Subscription> subscriptions =  subscriptionRepository.findAll();
        session.disableFilter("userId");
        return subscriptions;
    }

    public String deleteSubscription(long userId, long subscribedToUserId) {
        SubscriptionId subscriptionId = new SubscriptionId(userId, subscribedToUserId);
        if (subscriptionRepository.existsById(subscriptionId)) {
            subscriptionRepository.deleteById(subscriptionId);
            return String.format("Subscription user ID=%s to user ID=%s was deleted", userId, subscribedToUserId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
