package com.geeeky.linqr.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.geeeky.linqr.QR.Generate_QR;
import com.geeeky.linqr.Login.Login;
import com.geeeky.linqr.R;
import com.geeeky.linqr.QR.Scanner_Gallery;
import com.geeeky.linqr.QR.Scanner_Camera;

public class HomeScreen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView Name, Num;
    Button userQR, ScanUser, Gallery, Logout;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homescreen);

        initialization();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences2 = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences preferences3 = getSharedPreferences("save", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = preferences2.edit();
                SharedPreferences.Editor editor3 = preferences3.edit();
                editor2.putString("remember", "false");
                editor3.putString("save1", "false");
                preferences2.edit().remove("checkbox").commit();
                preferences3.edit().remove("save").commit();
                editor2.apply();
                editor3.apply();
                sp.edit().clear().clear().apply();
                Intent d = new Intent(HomeScreen.this, Login.class);
                startActivity(d);
                finishAffinity();
            }
        });

        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Scanner_Gallery.class);
                startActivity(i);
                finish();
            }
        });

        String name = sp.getString("saved_name", "");
        String num = sp.getString("saved_num", "");

        Name.setText("Name: " + name);
        Num.setText("Phone No: " + num);

        ScanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, Scanner_Camera.class);
                startActivity(intent);
                finish();
            }
        });

        userQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, Generate_QR.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initialization() {
        sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        Name = findViewById(R.id.name);
        Num = findViewById(R.id.num);
        userQR = findViewById(R.id.user_qr);
        ScanUser = findViewById(R.id.scan_user);
        Gallery = findViewById(R.id.gal);
        Logout = findViewById(R.id.logout);
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
                finishAffinity();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HomeScreen.this, "Exit cancelled", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Update_user:
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Intent b = new Intent(HomeScreen.this, Login.class);
                startActivity(b);
                finishAffinity();
                break;

            case R.id.Update_other:
                SharedPreferences preferences1 = getSharedPreferences("save", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences1.edit();
                editor1.putString("save1", "false");
                editor1.apply();
                Intent c = new Intent(HomeScreen.this, Login.class);
                startActivity(c);
                finishAffinity();
                break;

            case R.id.About_us:
                Intent a = new Intent(HomeScreen.this, About_us.class);
                startActivity(a);
                finish();
                break;

            default:
                return false;
        }
        return false;
    }
}