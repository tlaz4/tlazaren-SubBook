package com.example.subbook;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tlaz on 2018-02-03.
 */

public class SubscriptionData implements Serializable {
    private ArrayList<Subscription> subscriptions;

    public SubscriptionData(){
        subscriptions = new ArrayList<>();
    }

    public SubscriptionData(ArrayList<Subscription> subscriptions){
        this.subscriptions = subscriptions;
    }

    public ArrayList<Subscription> getSubscriptions(){
        return subscriptions;
    }

    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public void deleteSubscription(int index){
        subscriptions.remove(index);
    }

    public Subscription getSubscription(int i){
        return subscriptions.get(i);
    }

    public Double getTotalCharges(){
        double total = 0;

        for(int i = 0; i < subscriptions.size(); i++){
            total += subscriptions.get(i).getCharge();
        }

        return total;
    }



}
