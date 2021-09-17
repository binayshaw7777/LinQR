package com.geeeky.linqr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class GenQR extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView Name, Num;
    CardView userQR, SocialQR, ScanUser;
    Button update_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gen_qr);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);

        Name = findViewById(R.id.name);
        Num = findViewById(R.id.num);
     //   update_pro = findViewById(R.id.settings);
        userQR = findViewById(R.id.user_qr);
      //  SocialQR = findViewById(R.id.social_qr);
        ScanUser = findViewById(R.id.scan_user);


        String name=sp.getString("saved_name", "");
        String num=sp.getString("saved_num", "");

        Name.setText("Name: "+name);
        Num.setText("Phone No: "+num);

        ScanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenQR.this, Scan_User_QR.class);
                startActivity(intent);
                finish();
            }
        });

     /*   update_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                Intent intent1 = new Intent(GenQR.this, User_Details.class);
                startActivity(intent1);
                finish();
            }
        });*/

        userQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenQR.this, User_QR.class);
                startActivity(intent);
                finish();
            }
        });

        /*SocialQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenQR.this, update.class);
                startActivity(intent);
                finish();
            }
        });*/
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
                finishAffinity();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(GenQR.this, "Exit cancelled", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }
    public  void showPopup(View v){
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
                Intent b = new Intent(GenQR.this, User_Details.class);
                startActivity(b);
                finish();
                break;

            case R.id.About_us:
                Intent a = new Intent(GenQR.this, About_us.class);
                startActivity(a);
                finish();
                break;

            default:
                return false;
        }
        return false;
    }
}