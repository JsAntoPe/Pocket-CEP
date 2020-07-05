package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;

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

    public void removeStreamSubscription(String streamID, Subscription subscription) throws NotFoundException {
        if(!streamSubscriptions.containsKey(streamID)){
            throw new NotFoundException("The stream does not exist, or it does not have any subscriptions.");
        }
        if(!streamSubscriptions.get(streamID).contains(subscription)){
            throw new NotFoundException("This stream does not have this subscriber.");
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
        return streamSubscriptions.getOrDefault(streamID, null);
    }

    public String getSubscriptionsToString(String streamID) {
        List<Subscription> subscriptionsToPrint = getSubscriptions(streamID);
        if (subscriptionsToPrint == null){
            return "No subscriptions";
        }
        String subscriptions = "For the stream " + streamID + ", the subscriptions are:\n";

        for(Subscription subscription: subscriptionsToPrint){
            subscriptions += "\t" + subscription.toString() + "\n";
        }

        return subscriptions;
    }
}
