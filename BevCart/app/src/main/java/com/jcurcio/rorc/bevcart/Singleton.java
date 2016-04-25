package com.jcurcio.rorc.bevcart;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.core.Context;

/**
 * Created by Josh on 4/10/2016.
 */
public class Singleton {

    static Firebase ref = new Firebase("https://bevcart.firebaseio.com");
    static String role = "user";
    static AuthData authData;
    static Order[] orderArray = new Order[2];
    static void logout (Firebase ref)
    {
        ref.unauth();
    }
    static String[] completedViewArray = new String[2];
    static String[] pendingViewArray = new String[2];
    static String[] incompleteViewArray = new String[2];
    static String[] listViewArray = new String[2];
}
