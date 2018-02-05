package com.example.subbook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tlaz on 2018-01-23.
 */

public class Subscription implements Serializable{
    private String name;
    private Date date;
    private Double charge;
    private String comment;

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
