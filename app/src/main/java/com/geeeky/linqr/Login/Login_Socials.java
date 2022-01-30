package com.geeeky.linqr.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geeeky.linqr.Main.HomeScreen;
import com.geeeky.linqr.R;

public class Login_Socials extends AppCompatActivity {

    SharedPreferences sp;
    EditText Twitter, Insta, Linkedin, Discord, Facebook, Github;
    Button Cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_socials);

        Twitter = findViewById(R.id.twitter);
        Discord = findViewById(R.id.dis2);
        Facebook = findViewById(R.id.fb);
        Linkedin = findViewById(R.id.li);
        Github = findViewById(R.id.git);
        Insta = findViewById(R.id.instagram);
        Cont = findViewById(R.id.next);
        sp = getSharedPreferences("other_details", Context.MODE_PRIVATE);

        SharedPreferences preferences = getSharedPreferences("save",MODE_PRIVATE);
        String checkbox1 = preferences.getString("save1","");
        if (checkbox1.equals("true")){
            Intent intent = new Intent(Login_Socials.this, HomeScreen.class);
            startActivity(intent);
        }
        Cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("save",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("save1","true");
                editor1.apply();
                String txt_Insta = Insta.getText().toString().toLowerCase();
                String txt_Li = Linkedin.getText().toString().toLowerCase();
                String txt_Fb = Facebook.getText().toString().toLowerCase();
                String txt_git = Linkedin.getText().toString().toLowerCase();
                String txt_twitter = Twitter.getText().toString().toLowerCase();
                String dis = Discord.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("saved_Insta", txt_Insta);
                editor.putString("saved_Li", txt_Li);
                editor.putString("saved_Git", txt_git);
                editor.putString("saved_Fb", txt_Fb);
                editor.putString("saved_Twit", txt_twitter);
                editor.putString("saved_Dis", dis);
                editor.commit();
                Toast.makeText(Login_Socials.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_Socials.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}