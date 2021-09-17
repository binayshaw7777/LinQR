package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Display_user extends AppCompatActivity {

    TextView Name_Dis, Number_Dis;
    Button toHome, Rescan, Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display_user);

        Number_Dis = findViewById(R.id.number);
        Name_Dis = findViewById(R.id.dis_name);
        toHome = findViewById(R.id.to_home);
        Rescan = findViewById(R.id.rescan_user);
        Add = findViewById(R.id.add);

        Intent newInt = getIntent();
        String num = newInt.getExtras().getString("decoded");

        String Data = num;

        String number_display =Data.substring(0,10);
        String name_display = Data.substring(10);

        Name_Dis.setText("Name of the User:\n"+name_display);
        Number_Dis.setText("Phone Number of the User:\n"+number_display);

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent th = new Intent(Display_user.this, GenQR.class);
                startActivity(th);
                finish();
            }
        });

        Rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resc = new Intent(Display_user.this, Scan_User_QR.class);
                startActivity(resc);
                finishAffinity();
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
                intent.setType(ContactsContract.RawContacts.CONTENT_ITEM_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name_display);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, number_display);
                    startActivity(intent);
            }
        });
    }
}