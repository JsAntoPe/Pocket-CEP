package com.siddhiApi.dao;

import com.siddhiApi.entity.Subscription;

public interface SubscriptionDAO {
    String subscribe(String streamID, Subscription subscription);

    String getSubscriptions(String streamID);

    String getSubscriptions(String streamID, String subscriptionID);

    String unsubscribe(String streamID, String subscriptionID);
}
