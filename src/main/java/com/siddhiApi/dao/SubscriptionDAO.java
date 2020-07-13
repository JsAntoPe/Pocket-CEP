package com.siddhiApi.dao;

import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.NotFoundException;

import java.util.List;

public interface SubscriptionDAO {
    Subscription subscribe(String streamID, Subscription subscription);

    Subscription[] getSubscriptions(String streamID);

    Subscription getSubscription(String streamID, String subscriptionID) throws NotFoundException;

    String unsubscribe(String streamID, String subscriptionID);

    void removeAllSubscriptionsOfAStream(String streamID) throws NotFoundException;

    //String getSubscriptionsToString(String streamID);
}
