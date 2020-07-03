package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Subscription;

import java.util.*;

public class SubscriptionsDatabase {
    private Map<String, List<Subscription>> streamSubscriptions;
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

    public void addStreamSubscription(String streamID, Subscription subscription){
        if(!streamSubscriptions.containsKey(streamID)){
            streamSubscriptions.put(streamID, new ArrayList<>());
        }
        streamSubscriptions.get(streamID).add(subscription);
    }

    public void removeStreamSubscription(String streamID, Subscription subscription) throws Exception {
        if(!streamSubscriptions.containsKey(streamID)){
            throw new Exception("The stream does not have any subscriptions.");
        }
        if(!streamSubscriptions.get(streamID).contains(subscription)){
            throw new Exception("This stream does not have this subscriber.");
        }
        streamSubscriptions.get(streamID).remove(subscription);
        if(streamSubscriptions.get(streamID).size() == 0){
            streamSubscriptions.remove(streamID);
        }
    }

    public Map<String, List<Subscription>> getStreamSubscriptions() {
        return streamSubscriptions;
    }

    public void setStreamSubscriptions(Map<String, List<Subscription>> streamSubscriptions) {
        this.streamSubscriptions = streamSubscriptions;
    }

    public List<Subscription> getSubscriptions(String streamID){
        return streamSubscriptions.get(streamID);
    }

    public String getSubscriptionsToString(String streamID) {
        String subscriptions = "For the stream " + streamID + ", the subscriptions are:\n";

        for(Subscription subscription: getSubscriptions(streamID)){
            subscriptions += "\t" + subscription.toString() + "\n";
        }

        return subscriptions;
    }
}
