package com.example.subbook;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tlaz on 2018-02-03.
 */

/*
/ Class to implement all subscriptions, that is all Subscription objects are stored in this class,
/ to allow for transfer of subscriptions across activities
/ contains methods to access a particular subscription, and to delete or add a subscription
/ this class also implements getTotalCharge(), so that the sum of all subscription's charges can be easily retrieved
 */

public class SubscriptionData implements Serializable {
    /* Subscription ArrayList to store all subscription objects*/
    private ArrayList<Subscription> subscriptions;

    /* constructors */
    public SubscriptionData(){
        subscriptions = new ArrayList<>();
    }

    public SubscriptionData(ArrayList<Subscription> subscriptions){
        this.subscriptions = subscriptions;
    }

    /* get all subscriptions*/
    public ArrayList<Subscription> getSubscriptions(){
        return subscriptions;
    }

    /* add a subscription to subscriptions */
    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    /* delete a particular subscription by its index*/
    public void deleteSubscription(int index){
        subscriptions.remove(index);
    }

    /* get a particular subscription by its index*/
    public Subscription getSubscription(int i){
        return subscriptions.get(i);
    }

    /* iterate through all subscriptions and return the total monthly charge */
    public Double getTotalCharges(){
        double total = 0;

        for(int i = 0; i < subscriptions.size(); i++){
            total += subscriptions.get(i).getCharge();
        }
        return total;
    }
}
