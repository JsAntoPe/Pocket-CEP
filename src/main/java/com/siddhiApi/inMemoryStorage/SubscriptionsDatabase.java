package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.NotFoundException;
import io.siddhi.query.api.expression.condition.Not;
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

    public void removeStreamSubscription(String streamID, String subscriptionID) throws NotFoundException {
        if(!streamSubscriptions.containsKey(streamID)){
            throw new NotFoundException("The stream does not exist, or it does not have any subscriptions.");
        }
        List<Subscription> subscriptionsOfTheStream = streamSubscriptions.get(streamID);
        if (subscriptionsOfTheStream == null || subscriptionsOfTheStream.size() == 0){
            throw new NotFoundException("The stream does not have any subscription");
        }
        int indexOfSubscription = -1;
        for(Subscription subscription: subscriptionsOfTheStream){
            if (subscription.getIdentifier().equals(subscriptionID)){
                indexOfSubscription = subscriptionsOfTheStream.indexOf(subscription);
            }
        }
        if (indexOfSubscription == -1){
            throw new NotFoundException("This stream does not have this subscriber.");
        }
        else{
            streamSubscriptions.get(streamID).remove(indexOfSubscription);
            if(streamSubscriptions.get(streamID).size() == 0){
                streamSubscriptions.remove(streamID);
            }
        }
    }

    public Map<String, List<Subscription>> getStreamSubscriptions() {
        return streamSubscriptions;
    }

    public void setStreamSubscriptions(Map<String, List<Subscription>> streamSubscriptions) {
        this.streamSubscriptions = streamSubscriptions;
    }

    public Subscription[] getSubscriptions(String streamID) {
        if(!streamSubscriptions.containsKey(streamID)){
            return new Subscription[0];
        }
        return streamSubscriptions.get(streamID).toArray(new Subscription[streamSubscriptions.size()]);
    }

    public Subscription getSubscription(String streamID, String id) throws NotFoundException {
        Subscription[] subscriptions = getSubscriptions(streamID);
        for (Subscription subscription: subscriptions){
            if (subscription.getIdentifier().equals(id)){
                return subscription;
            }
        }
        throw new NotFoundException("The subscription does not exist.");
    }

    public void removeAllStreamSubscriptions(String streamID) throws NotFoundException {
        if(!streamSubscriptions.containsKey(streamID)){
            throw new NotFoundException("The stream does not exist, or it does not have any subscriptions.");
        }
        streamSubscriptions.remove(streamID);
    }

    /*public String getSubscriptionsToString(String streamID) {
        List<Subscription> subscriptionsToPrint = getSubscriptions(streamID);
        if (subscriptionsToPrint == null){
            return "No subscriptions";
        }
        String subscriptions = "For the stream " + streamID + ", the subscriptions are:\n";

        for(Subscription subscription: subscriptionsToPrint){
            subscriptions += "\t" + subscription.toString() + "\n";
        }

        return subscriptions;
    }*/
}
