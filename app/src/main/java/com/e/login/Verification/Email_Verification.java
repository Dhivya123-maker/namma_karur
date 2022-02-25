package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.login.R;

public class Email_Verification extends AppCompatActivity {

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        send = findViewById(R.id.send_mail);


       send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otp();
                Intent intent = new Intent(Email_Verification.this,Email_OTP.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });


    }
}