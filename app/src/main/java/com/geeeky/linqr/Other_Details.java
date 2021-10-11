package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Other_Details extends AppCompatActivity {

    SharedPreferences sp;
    EditText Twitter, Insta, Linkedin, Discord, Facebook, Github;
    Button Cont;
    CheckBox save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_other_details);

        Twitter = findViewById(R.id.twitter);
        Discord = findViewById(R.id.dis2);
        Facebook = findViewById(R.id.fb);
        Linkedin = findViewById(R.id.li);
        Github = findViewById(R.id.git);
        Insta = findViewById(R.id.instagram);
        Cont = findViewById(R.id.next);
        save = findViewById(R.id.save);

        sp = getSharedPreferences("other_details", Context.MODE_PRIVATE);

        SharedPreferences preferences = getSharedPreferences("save",MODE_PRIVATE);
        String checkbox1 = preferences.getString("save1","");
        if (checkbox1.equals("true")){
            Intent intent = new Intent(Other_Details.this, HomeScreen.class);
            startActivity(intent);
        }
        /*else if (checkbox.equals("false")){
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }*/

        /*if(Twitter.length()==0){
            Twitter.setText("NULL");
        }
        if(Discord.length()==0){
            Discord.setText("NULL");
        }
        if(Facebook.length()==0){
            Facebook.setText("NULL");
        }
        if(Linkedin.length()==0){
            Linkedin.setText("NULL");
        }
        if(Github.length()==0){
            Github.setText("NULL");
        }
        if(Insta.length()==0){
            Insta.setText("NULL");
        }*/

        /*if(Discord.toString()==""){
            Discord.setText("NULL");
        }*/

        Cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Toast.makeText(Other_Details.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Other_Details.this, HomeScreen.class);
                    startActivity(intent);
                    finish();
                    }
            });

        save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("save",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("save1","true");
                    editor.apply();
                }
                else if (!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("save",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("save1","false");
                    editor.apply();
                }
            }
        });

    }
}