package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Creates the detail page of each business in Firebase that's been clicked on
 * @author Juliano Franz, Sitanun Changhor
 */
public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, addressField;
    private Spinner primaryBusinessField, provinceField;
    private TextView primaryBusinessTextField, provinceTextField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    /**
     * Generates the input, text, spinners and buttons
     * @param savedInstanceState Previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());

        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (Spinner) findViewById(R.id.primaryBusiness);
        primaryBusinessTextField = (TextView) findViewById(R.id.primaryBusinessText);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);
        provinceTextField = (TextView) findViewById(R.id.provinceText);

        if(receivedBusinessInfo != null){
            numberField.setText(receivedBusinessInfo.number);
            nameField.setText(receivedBusinessInfo.name);
            primaryBusinessField.setSelected(Boolean.parseBoolean(receivedBusinessInfo.primaryBusiness));
            primaryBusinessTextField.setText(receivedBusinessInfo.primaryBusiness);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setSelected(Boolean.parseBoolean(receivedBusinessInfo.province));
            provinceTextField.setText(receivedBusinessInfo.province);
        }
    }

    /**
     * Updates the business on Firebase with whatever was given on this page
     * @param v View object
     */
    public void updateContact(View v){
        receivedBusinessInfo.number = numberField.getText().toString();
        receivedBusinessInfo.name = nameField.getText().toString();
        receivedBusinessInfo.primaryBusiness = primaryBusinessField.getSelectedItem().toString();
        primaryBusinessTextField.setText(receivedBusinessInfo.primaryBusiness);
        receivedBusinessInfo.address = addressField.getText().toString();
        receivedBusinessInfo.province = provinceField.getSelectedItem().toString();
        provinceTextField.setText(receivedBusinessInfo.province);

        appState.firebaseReference.child(receivedBusinessInfo.uid).setValue(receivedBusinessInfo);

        finish();
    }

    /**
     * Erases the business from Firebase
     * @param v View object
     */
    public void eraseContact(View v) {
        appState.firebaseReference.child(receivedBusinessInfo.uid).removeValue();

        finish();
    }
}
