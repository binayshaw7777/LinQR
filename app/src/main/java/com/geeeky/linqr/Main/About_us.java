package com.geeeky.linqr.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.geeeky.linqr.R;

public class About_us extends AppCompatActivity {

    Button Linktree, Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about_us);

            Linktree = (Button) findViewById(R.id.link);
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if(networkInfo==null || !networkInfo.isConnected() || !networkInfo.isAvailable())
            {
                Toast.makeText(About_us.this, "Not connected to internet!", Toast.LENGTH_SHORT).show();
            }

            Linktree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl("https://linktr.ee/GAMIX7");
                }
            });

            Back = (Button) findViewById(R.id.back);

            Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(About_us.this, HomeScreen.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        }
        private void gotoUrl(String s) {
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }
    }