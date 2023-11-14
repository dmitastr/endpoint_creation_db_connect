package ru.skillbox.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.entity.SubscriptionId;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    @Override
    List<Subscription> findAll();

    void deleteById(SubscriptionId subscriptionId);

    boolean existsById(SubscriptionId subscriptionId);

}
