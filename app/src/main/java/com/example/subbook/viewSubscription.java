package com.example.subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/*
/ Class with interface to allow the user to view the details of a subscription, or modify an existing subscription
/ Submission of a edited subscription will take the user back to mainActivity and view all subscriptions
/ Also implements the functionality to delete the current subscription from the list
/ Subscriptions are retrieved by passing the SubscriptionData object, and the index of the clicked subscription to this class
 */
public class viewSubscription extends AppCompatActivity {

    /* variables for this class */
    private EditText name;
    private EditText charge;
    private EditText date;
    private EditText comment;
    private Button button;
    private Button delete;

    private SubscriptionData subscriptions;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* get all xml elements */
        setContentView(R.layout.activity_view_subscription);
        index = getIntent().getExtras().getInt("index");
        subscriptions = (SubscriptionData)getIntent().getExtras().getSerializable("subscriptions");
        name = (EditText) findViewById(R.id.SubName);
        charge = findViewById(R.id.SubCharge);
        date = findViewById(R.id.SubDate);
        comment = findViewById(R.id.subComment);
        button = findViewById(R.id.button);
        delete = findViewById(R.id.delete);

        /* set the text of editable textboxes to the subscription data*/
        name.setText(subscriptions.getSubscription(index).getName());
        charge.setText(subscriptions.getSubscription(index).getCharge().toString());
        date.setText(subscriptions.getSubscription(index).getDate().toString());
        comment.setText(subscriptions.getSubscription(index).getComment());

        /* add listener to modify a subscription, using setters to update the subscription object,
        * and then returning to the mainActivity*/
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date newDate = createSubscription.stringToDate(date.getText().toString());

                subscriptions.getSubscription(index).setName(name.getText().toString());
                subscriptions.getSubscription(index).setCharge(Double.parseDouble(charge.getText().toString()));
                subscriptions.getSubscription(index).setDate(newDate);
                subscriptions.getSubscription(index).setComment(comment.getText().toString());

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("subscriptions", subscriptions);
                startActivity(intent);
            }
        });

        /* set listener to delete a subscription, then return to the mainActivity */
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                subscriptions.deleteSubscription(index);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("subscriptions", subscriptions);
                startActivity(intent);
            }
        });
    }
}
