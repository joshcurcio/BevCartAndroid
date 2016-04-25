package com.jcurcio.rorc.bevcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class user_home extends AppCompatActivity {

    private Button userLogout;
    private Button createOrder;
    private Firebase ref = new Firebase("https://bevcart.firebaseio.com");
    private Button orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_home);
        Firebase.setAndroidContext(this);

        if (ref.getAuth() == null) {
            startActivity(new Intent(user_home.this, MainActivity.class));
        }

        userLogout = (Button) findViewById(R.id.userLogout);
        createOrder = (Button) findViewById(R.id.createOrder);
        orderHistory = (Button) findViewById(R.id.orderHistoryButton);

        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.unauth();
                startActivity(new Intent(user_home.this, MainActivity.class));
            }
        });

        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_home.this, OrderScreen.class));
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_home.this, ListViewScreen.class));
            }
        });
    }
}
