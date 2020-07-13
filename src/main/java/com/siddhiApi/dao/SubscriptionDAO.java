package com.siddhiApi.dao;

import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.NotFoundException;

public interface SubscriptionDAO {
    Subscription subscribe(String streamID, Subscription subscription);

    Subscription[] getSubscriptions(String streamID);

    Subscription getSubscription(String streamID, String subscriptionID) throws NotFoundException;

    void unsubscribe(String streamID, String subscriptionID) throws NotFoundException;

    void removeAllSubscriptionsOfAStream(String streamID) throws NotFoundException;

    //String getSubscriptionsToString(String streamID);
}
