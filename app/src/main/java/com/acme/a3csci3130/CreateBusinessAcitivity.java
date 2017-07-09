package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Creates the create business page
 * @author Juliano Franz, Sitanun Changhor
 */
public class CreateBusinessAcitivity extends Activity {

    private Button createButton;
    private EditText numberField, nameField, addressField;
    private Spinner primaryBusinessField, provinceField;
    private MyApplicationData appState;

    /**
     * Generates the buttons, input and spinners on create
     * @param savedInstanceState Previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (Spinner) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);
        createButton = (Button) findViewById(R.id.createButton);
    }

    /**
     * On click, it'll pass in the result of the business to Firebase
     * @param v View object
     */
    public void submitInfoButton(View v) {
        String personID = appState.firebaseReference.push().getKey();//uid
        String number = numberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        Business business = new Business(personID, number, name, primaryBusiness, address, province);

        appState.firebaseReference.child(personID).setValue(business);

        finish();
    }
}
