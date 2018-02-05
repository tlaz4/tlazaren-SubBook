package com.example.subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
/ class for the user to create a new subscription
/ handles string to valid Date object functionality
/ creating a new subscription will take the user to a list consisting of all subscriptions
 */
public class createSubscription extends AppCompatActivity {
    /* variables of this class */
    private EditText name;
    private EditText charge;
    private EditText date;
    private EditText comment;
    private Button button;
    private Subscription newSubscription;
    private SubscriptionData subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subscription);

        /* retrieve all xml elements */
        name = findViewById(R.id.newSubName);
        charge = findViewById(R.id.newSubCharge);
        date = findViewById(R.id.newSubDate);
        comment = findViewById(R.id.newSubComment);
        button = findViewById(R.id.submitSub);

        /* get all existing subscriptions so we can add to them*/
        subscriptions = (SubscriptionData)getIntent().getExtras().getSerializable("createSubs");

        /* set click event to create a new subscriptions, getting all data, formatting it,
        * passing it into a new subscription object, and adding it to SubscriptionData, then return to the mainActivity*/
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newName = name.getText().toString();
                Date newDate = stringToDate(date.getText().toString());
                Double newCharge = Double.parseDouble(charge.getText().toString());
                String newComment = comment.getText().toString();

                newSubscription = new Subscription(newName, newDate, newCharge, newComment);

                subscriptions.addSubscription(newSubscription);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("subscriptions", subscriptions);
                startActivity(intent);
            }
        });

    }

    /*
    / https://stackoverflow.com/questions/9945072/convert-string-to-date-in-java
    / Boris Strandjev
    / A function to convert a string yyyy-mm-dd into a valid Date object
    */
    static public Date stringToDate(String dateStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date newDate = new Date();
        try {
            newDate = dateFormat.parse(dateStr);
        }catch(ParseException e){
            e.printStackTrace();
        }

        return newDate;
    }

}
