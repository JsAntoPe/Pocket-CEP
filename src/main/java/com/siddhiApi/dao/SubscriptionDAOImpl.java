package com.siddhiApi.dao;

import com.siddhiApi.entity.Subscription;
import com.siddhiApi.inMemoryStorage.SubscriptionsDatabase;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionDAOImpl implements SubscriptionDAO{

    private final SubscriptionsDatabase subscriptionsDatabase = SubscriptionsDatabase.getSubscriptionsDatabase();

    @Override
    public String subscribe(String streamID, Subscription subscription) {
        subscriptionsDatabase.addStreamSubscription(streamID, subscription);
        return subscription.getIdentifier();
    }

    @Override
    public String getSubscriptions(String streamID) {
        return subscriptionsDatabase.getSubscriptionsToString(streamID);
    }

    @Override
    public String getSubscriptions(String streamID, String subscriptionID) {
        return null;
    }

    @Override
    public String unsubscribe(String streamID, String subscriptionID) {
        return null;
    }
}
