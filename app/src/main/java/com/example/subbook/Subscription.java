package com.example.subbook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tlaz on 2018-01-23.
 */

/*
/ This class is the actual Subscription object, handling all data and functions associated with a subscription
/ the data for a Subscription is stored in this object, with its respective getter and setters to access
/ the data for a particular subscription object
/ contains a function toString() to return a string representation of the object for display purposes
 */

public class Subscription implements Serializable{
    private String name;
    private Date date;
    private Double charge;
    private String comment;

    /* cosntructors for Subscription */
    Subscription(String name, Double charge, String comment){
        this.name = name;
        date = new Date();
        this.charge = charge;
        this.comment = comment;
    }

    Subscription(String name, Date date, Double charge, String comment){
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    /* getter and setters for subscriptions object */
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public void setCharge(Double charge){
        this.charge = charge;
    }

    public Double getCharge(){
        return charge;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public String toString(){
        return name + "\n" +"$" + charge.toString() + "\n" + date.toString();
    }
}
