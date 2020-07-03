package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Subscription;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubscriptionsDatabase {
    private Map<String, Subscription> streamSubscriptions;
    private static SubscriptionsDatabase subscriptionsDatabase;


    private SubscriptionsDatabase() {
        this.streamSubscriptions = new HashMap<>();
    }

    public static SubscriptionsDatabase getSubscriptionsDatabase() {
        if(subscriptionsDatabase == null){
            subscriptionsDatabase = new SubscriptionsDatabase();
        }
        return subscriptionsDatabase;
    }
}
