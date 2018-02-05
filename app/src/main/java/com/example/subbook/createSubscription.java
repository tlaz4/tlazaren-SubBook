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

public class createSubscription extends AppCompatActivity {
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

        name = findViewById(R.id.newSubName);
        charge = findViewById(R.id.newSubCharge);
        date = findViewById(R.id.newSubDate);
        comment = findViewById(R.id.newSubComment);
        button = findViewById(R.id.submitSub);

        subscriptions = (SubscriptionData)getIntent().getExtras().getSerializable("createSubs");

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
    /adopted from http://www.java2s.com/Tutorial/Java/0040__Data-Type/SimpleDateFormat.htm
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
