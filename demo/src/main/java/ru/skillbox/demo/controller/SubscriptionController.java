package ru.skillbox.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping(value="/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    String createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @GetMapping(value="/{userId}")
    List<Subscription> getUserSubscriptions(@PathVariable long userId) {
        return subscriptionService.getUsersSubscriptions(userId);
    }

    @DeleteMapping(value = "/{userId}/{subscribedToUserId}")
    String deleteSubscription(@PathVariable long userId, @PathVariable long subscribedToUserId) {
        return  subscriptionService.deleteSubscription(userId, subscribedToUserId);
    }
}
