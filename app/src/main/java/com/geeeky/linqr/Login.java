package com.geeeky.linqr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    Button proceed;
    CheckBox remember;
    EditText Name, Phone, Insta, Email, Linkedin;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_details);

        proceed = findViewById(R.id.next);
        Name = findViewById(R.id.editTextTextPersonName);
        Phone = findViewById(R.id.editTextPhone);
        remember = findViewById(R.id.checkBox);
        Insta = findViewById(R.id.insta);
        Email = findViewById(R.id.email);
        Linkedin = findViewById(R.id.linked);

        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")){
            Intent intent = new Intent(Login.this, HomeScreen.class);
            startActivity(intent);
        }else if (checkbox.equals("false")){
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name.length()==0 || Phone.length()<10 || Email.length()<10)
                {
                    Toast.makeText(Login.this, "Enter your Details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    String txt_name = Name.getText().toString();
                   String txt_num = Phone.getText().toString();
                   String txt_email = Email.getText().toString();
                   String txt_LI = Linkedin.getText().toString().toLowerCase();
                   String txt_Insta = Insta.getText().toString().toLowerCase();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("saved_name", txt_name);
                    editor.putString("saved_num", txt_num);
                    editor.putString("saved_email", txt_email);
                    editor.putString("saved_linkedin", txt_LI);
                    editor.putString("saved_insta", txt_Insta);
                    editor.commit();
                    Toast.makeText(Login.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, HomeScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                }
                else if (!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });
    }
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Exit");
        alertDialogBuilder.setIcon(R.drawable.logo);
        alertDialogBuilder.setMessage("Do you really want to exit?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Login.this, "Exit cancelled", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }
}