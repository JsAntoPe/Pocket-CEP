package com.siddhiApi.dao;

import com.siddhiApi.entity.Subscription;

import java.util.List;

public interface SubscriptionDAO {
    String subscribe(String streamID, Subscription subscription);

    List<Subscription> getSubscriptions(String streamID);

    Subscription getSubscriptions(String streamID, String subscriptionID);

    String unsubscribe(String streamID, String subscriptionID);

    String getSubscriptionsToString(String streamID);
}
