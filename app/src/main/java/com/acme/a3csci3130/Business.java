package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 * @author Juliano Franz, Sitanun Changhor
 */

public class Business implements Serializable {

    public  String uid;
    public  String number;
    public  String name;
    public  String primaryBusiness;
    public  String address;
    public  String province;

    /**
     * Default constructor required for calls to DataSnapshot.getValue
     */
    public Business() {}

    /**
     * Constructor for the Business object
     * @param uid User ID on Firebase
     * @param number Number of the business
     * @param name Name of the business
     * @param primaryBusiness Primary business type of the business
     * @param address Address of the business
     * @param province Province of the business
     */
    public Business(String uid, String number, String name, String primaryBusiness, String address, String province){
        this.uid = uid;
        this.number = number;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    /**
     * Stores the Business object
     * @return Returns the result
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("number", number);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
