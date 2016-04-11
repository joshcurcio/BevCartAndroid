package com.jcurcio.rorc.bevcart;

import com.firebase.client.Firebase;

/**
 * Created by Josh on 4/10/2016.
 */
public class Singleton {

    void logout (Firebase ref)
    {
        ref.unauth();
    }
}
