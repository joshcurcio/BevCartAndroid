package com.jcurcio.rorc.bevcart;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button signUp;
    private TextView userEmail;
    private TextView userPassword;
    private TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);


        if(Singleton.ref.getAuth() != null)
        {
            Singleton.authData = Singleton.ref.getAuth();
            startActivity(new Intent(MainActivity.this, user_home.class));
        }

        login = (Button)findViewById(R.id.loginButton);
        signUp = (Button)findViewById(R.id.signUpBut);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userPassword = (TextView) findViewById(R.id.userPassword);

        test = (TextView) findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Create a handler to handle the result of the authentication
                login(userEmail.getText().toString(), userPassword.getText().toString());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.ref.createUser(userEmail.getText().toString(), userPassword.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                        final Firebase roleRef = Singleton.ref.child("role");

                        Map<String, String> post1 = new HashMap<String, String>();
                        roleRef.child(result.get("uid").toString()).setValue("user");
                        test.setText("user Created");
                        login(userEmail.getText().toString(), userPassword.getText().toString());
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                        test.setText(firebaseError.toString());
                    }
                });
            }
        });
    }

    private void login(String username, String password)
    {
        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
                test.setText("Logged In");
                Singleton.authData = Singleton.ref.getAuth();

                Singleton.ref.child("role").child(Singleton.authData.getUid().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Singleton.role = snapshot.getValue().toString();
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                });
                if (Singleton.role == "user") {
                    startActivity(new Intent(MainActivity.this, user_home.class));
                } else if (Singleton.role == "provider"){

                } else if (Singleton.role == "admin") {

                } else {
                    Log.d("ERROR", "INVALID ROLE");
                }
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
                test.setText(firebaseError.toString());
            }
        };
        // Or with an email/password combination
        Singleton.ref.authWithPassword(username, password, authResultHandler);

    }

}

