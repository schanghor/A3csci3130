package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Displays main app (i.e. create contact and the list of business in Firebase
 * @author Juliano Franz, Sitanun Changhor
 */
public class MainActivity extends Activity {
    private ListView contactListView;
    private FirebaseListAdapter<Business> firebaseAdapter;

    /**
     * Creates the UI for the main app
     * @param savedInstanceState Previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("business");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class, android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business person = (Business) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    /**
     * Creates the contact button. On click, it'll go to the create business page
     * @param v View object
     */
    public void createContactButton(View v) {
        Intent intent=new Intent(this, CreateBusinessAcitivity.class);
        startActivity(intent);
    }

    /**
     * Shows the list of business that's on Firebase on the main app page
     * @param person Business object
     */
    private void showDetailView(Business person) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Business", person);
        startActivity(intent);
    }
}
