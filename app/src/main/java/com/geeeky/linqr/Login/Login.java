package com.geeeky.linqr.Login;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.geeeky.linqr.R;


public class Login extends AppCompatActivity {

    Button proceed;
    CheckBox remember;
    EditText Name, Phone, Email;
    SharedPreferences sp;
    ImageButton add;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        initialization();

        KeyboardFocusChange();

        rememberMeCheck();
    }

    private void rememberMeCheck() {
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(Login.this, Login_Socials.class);
            startActivity(intent);
        } else if (checkbox.equals("false")) {
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }
    }

    private void KeyboardFocusChange() {
        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void initialization() {
        proceed = findViewById(R.id.cont);
        add = findViewById(R.id.imageView6);
        Name = findViewById(R.id.editTextTextPersonName);
        Phone = findViewById(R.id.editTextPhone);
        remember = findViewById(R.id.checkBox);
        Email = findViewById(R.id.email);
        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);
    }

    public void onBackPressed() {
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
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 121 && resultCode == RESULT_OK)
            startActivity(new Intent(Login.this, Login.class));
    }

    public void LoginData(View view) {
        if (Name.length() == 0 || Phone.length() < 10 || Email.length() < 10) {
            Toast.makeText(Login.this, "Check your details", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = preferences.edit();
            editor1.putString("remember", "true");
            editor1.apply();
            String txt_name = Name.getText().toString();
            String txt_num = Phone.getText().toString();
            String txt_email = Email.getText().toString();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("saved_name", txt_name);
            editor.putString("saved_num", txt_num);
            editor.putString("saved_email", txt_email);
            editor.apply();
            Toast.makeText(Login.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, Login_Socials.class);
            startActivity(intent);
            finish();
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}