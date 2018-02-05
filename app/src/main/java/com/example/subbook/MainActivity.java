package com.example.subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
/ Main activity class of the app
/ Handles the the listview of subscriptions to view all, also serves as the main navigation page
/ to create a new subscription or modify an existing one
 */
public class MainActivity extends AppCompatActivity {
    /* variables of this class */
    private static final String FILENAME = "subs.sav";

    private ListView allSubscriptions;
    private SubscriptionData subscriptions;
    private ArrayAdapter<Subscription> adapter;
    private TextView total;
    private Button create;
    private ArrayList<Subscription> subs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* get intent from other activities, if intent exists, update the subscriptionData with the new subscriptionData
        * containing all subscriptions
        * else no intent exists, we must load from file the subscriptions
        */
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            subscriptions = (SubscriptionData) intent.getExtras().getSerializable("subscriptions");
        }else{
            loadFromFile();
        }

        /*get all xml elements*/
        total = findViewById(R.id.total);
        create = findViewById(R.id.create);
        allSubscriptions = (ListView) findViewById(R.id.allSubscriptions);

        /* set a click event for each subscription element so they can be viewed in detail
        * passing the SubscriptionData object and the index of the element to viewSubscription*/
        allSubscriptions.setClickable(true);
        allSubscriptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), viewSubscription.class);
                intent.putExtra("subscriptions", subscriptions);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });

        /* set click event to handle create subcription, pass to createSubscription with SubscriptionData*/
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), createSubscription.class);
                intent.putExtra("createSubs", subscriptions);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        /* create a new adapter from SubscriptionData's list to a listview */
        adapter = new ArrayAdapter<Subscription>(this,
                android.R.layout.simple_list_item_1, subscriptions.getSubscriptions() );
        allSubscriptions.setAdapter(adapter);

        /* get the total of all subscriptions and display it */
        total.setText("Total: $" + (subscriptions.getTotalCharges()).toString());
        saveInFile();
    }

    // adapted from https://github.com/ta301-ks/lonelyTwitter/blob/w18TueLab3/app/src/main/java/
    // ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
    // loads existing subscriptions from a file
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-23
            Type listType = new TypeToken<ArrayList<Subscription>>() {
            }.getType();
            subs = gson.fromJson(in, listType);
            subscriptions = new SubscriptionData(subs);

        } catch (FileNotFoundException e) {
            subscriptions = new SubscriptionData();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // adapted from https://github.com/ta301-ks/lonelyTwitter/blob/w18TueLab3/app/src/main/java
    // /ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
    // saves existing subscriptions to a file
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subscriptions.getSubscriptions(), out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
