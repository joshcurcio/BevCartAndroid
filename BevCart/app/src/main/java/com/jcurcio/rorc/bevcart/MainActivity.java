package com.jcurcio.rorc.bevcart;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private TextView userEmail;
    private TextView userPassword;
    private TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bevcart.firebaseio.com");

        if(ref.getAuth() != null)
        {
            startActivity(new Intent(MainActivity.this, user_home.class));
        }

        login = (Button)findViewById(R.id.loginButton);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userPassword = (TextView) findViewById(R.id.userPassword);

        test = (TextView) findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                // Create a handler to handle the result of the authentication
                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authenticated successfully with payload authData
                        test.setText("Logged In");
                        startActivity(new Intent(MainActivity.this, user_home.class));
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // Authenticated failed with error firebaseError
                        test.setText(firebaseError.toString());
                    }
                };
                // Or with an email/password combination
                ref.authWithPassword(userEmail.getText().toString(), userPassword.getText().toString(), authResultHandler);
            }
        });
    }

}

