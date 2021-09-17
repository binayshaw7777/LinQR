package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class update extends AppCompatActivity {

    Button back;
    CardView user, social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update);

        back = findViewById(R.id.back);
        user = findViewById(R.id.user_update);
      //  social = findViewById(R.id.social);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update.this, GenQR.class);
                startActivity(intent);
                finish();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                Intent intent1 = new Intent(update.this, User_Details.class);
                startActivity(intent1);
                finish();
            }
        });

     /*   social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(update.this, Socials.class);
                startActivity(intent2);
                finish();
            }
        });*/



    }
}