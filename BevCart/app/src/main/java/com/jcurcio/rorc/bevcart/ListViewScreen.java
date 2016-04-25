package com.jcurcio.rorc.bevcart;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class ListViewScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_screen);
        Firebase.setAndroidContext(this);

        final RadioButton pending = (RadioButton) findViewById(R.id.radPendingList);
        final RadioButton inComplete = (RadioButton) findViewById(R.id.radAcceptedOrders);
        final RadioButton completed = (RadioButton) findViewById(R.id.radCompletedOrders);

        if (Singleton.role == "user") {

            Singleton.ref.child("orders").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("There are ", snapshot.getChildrenCount() + " orders");
                    int i = 0;
                        for (DataSnapshot postSnapshot : snapshot.getChildren())
                        {
                            Order userOrder = postSnapshot.getValue(Order.class);
                            if (userOrder.getUser() == Singleton.authData.getUid()) {
                                Singleton.listViewArray[i] = userOrder.getKey();
                            }

                        }
                        populateListView(Singleton.listViewArray);

                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.d("The read failed: ", firebaseError.getMessage().toString());
                }
            });


        } else if (Singleton.role == "provider"){

        } else if (Singleton.role == "admin") {

        } else {
            Log.d("ERROR", "INVALID ROLE");
        }
    }

    public void populateListView(String[] theListViewArray)
    {
            ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theListViewArray);
            ListView theListView = (ListView) findViewById(R.id.listView);
            theListView.setAdapter(theAdapter);
    }
}
