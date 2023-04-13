package com.example.finalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Donation extends AppCompatActivity implements PaymentResultListener {

    FirebaseAuth mAuth;
    FirebaseUser user;
    String email;
    private EditText amountEdt, ph_number;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        user =mAuth.getCurrentUser();
        email =user.getEmail();

        // initializing all our variables.
        amountEdt = findViewById(R.id.idEdtAmount);
        ph_number = findViewById(R.id.ph_number);
        payBtn = findViewById(R.id.idBtnPay);

        // adding on click listener to our button.
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // amount that is entered by user.
                String samount = amountEdt.getText().toString();
                String phnumber = ph_number.getText().toString();

                if (TextUtils.isEmpty(samount) || TextUtils.isEmpty(phnumber))
                    amountEdt.setError("This field cannot be empty");


                else {

                    // rounding off the amount.
                    int amount = Math.round(Float.parseFloat(samount) * 100);

                    // initialize Razorpay account.
                    Checkout checkout = new Checkout();

                    // set your id as below
                    checkout.setKeyID("rzp_test_bYh9kKGWAILAhv");



                    // initialize json object
                    JSONObject object = new JSONObject();
                    try {
                        // to put name
                        object.put("name", "Grand Phone");

                        // put description
                        object.put("description", "Test payment");

                        // to set theme color
                        object.put("theme.color", "#25383C");

                        // put the currency
                        object.put("currency", "INR");

                        // put amount
                        object.put("amount", amount);

                        // put mobile number
                        object.put("prefill.contact", phnumber);

                        // put email
                        object.put("prefill.email", email);

                        // open razorpay to checkout activity
                        checkout.open(Donation.this, object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}