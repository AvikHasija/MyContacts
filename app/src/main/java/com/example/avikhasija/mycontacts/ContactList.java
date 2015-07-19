package com.example.avikhasija.mycontacts;

import java.util.ArrayList;

/**
 * Created by Avikshit on 7/19/2015.
 * GLOBAL CONTACT LIST ARRAY
 * Everything in project has access
 */
public class ContactList extends ArrayList<Contact> {
    private static ContactList sInstance = null;

    private ContactList(){

    }

    public static ContactList getInstance(){
        if(sInstance == null){
            sInstance = new ContactList();
        }
        return sInstance;
    }
}
