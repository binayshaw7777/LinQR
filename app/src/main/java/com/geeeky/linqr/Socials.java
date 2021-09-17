package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Socials extends AppCompatActivity {

    Button proceed;
    EditText Instagram, Twitter, Email, Linkedin;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_socials);

        proceed = findViewById(R.id.next);
        Instagram = findViewById(R.id.instagram);
        Twitter = findViewById(R.id.twitter);
        Email = findViewById(R.id.email);
        Linkedin = findViewById(R.id.linked);

        sp1 = getSharedPreferences("socials", Context.MODE_PRIVATE);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String insta = Instagram.getText().toString();
                String tweet = Twitter.getText().toString();
                String mail = Email.getText().toString();
                String link = Linkedin.getText().toString();

                SharedPreferences.Editor editor = sp1.edit();
                editor.putString("saved_insta", insta);
                editor.putString("saved_tweet", tweet);
                editor.putString("saved_mail", mail);
                editor.putString("saved_link", link);

                editor.commit();
                Toast.makeText(Socials.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Socials.this, GenQR.class);
                startActivity(intent);
                finish();
            }
        });


    }
}