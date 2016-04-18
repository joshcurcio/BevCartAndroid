package com.jcurcio.rorc.bevcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stripe.android.*;
import com.stripe.android.model.Card;

import java.io.Console;

public class PaymentActivity extends AppCompatActivity {

    private Button submitPayment = (Button) findViewById(R.id.submitPaymentBut);
    private TextView cardExpYear = (TextView) findViewById(R.id.cardExpYear);
    private TextView cardExpMonth = (TextView) findViewById(R.id.cardExpMonth);
    private TextView cardNumber = (TextView) findViewById(R.id.cardNum);
    private TextView cardCVC = (TextView) findViewById(R.id.cardCVC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



        submitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = new Card(
                        cardNumber.getText().toString(),
                        cardExpMonth.getText().hashCode(),
                        cardExpYear.getText().hashCode(),
                        cardCVC.getText().toString()
                );

                card.validateNumber();
                card.validateCVC();
            }
        });
    }
}
