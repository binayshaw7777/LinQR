package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ResultDisplay extends AppCompatActivity {

    TextView Name_Dis, Number_Dis, Email_Dis, Insta_Dis, LinkedIn_Dis;
    Button toHome, Rescan, Add, Add_insta, Add_LinkedIn;

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
        Add = findViewById(R.id.add_number);
        Email_Dis = findViewById(R.id.email);
        Insta_Dis = findViewById(R.id.instagram);
        Add_insta = findViewById(R.id.add_insta);
        LinkedIn_Dis = findViewById(R.id.Linkedin);
        Add_LinkedIn = findViewById(R.id.add_LinkedIn);

        Intent newInt = getIntent();
        String num = newInt.getExtras().getString("decoded");

      //  String Data = num;

   //     String[] split = num.split(":");
        String[] split = num.split(":");
      //  for(String:num)
        String name_display = split[0];
        String number_display = split[1];
        String email_display = split[2];
        String insta_display = split[3]; //+
        String linkedin_display = split[4];

        String Insta_To_Site = "https://www.instagram.com/"+insta_display;
        String LinkedIn_To_Site = "https://www.linkedin.com/in/"+linkedin_display;


       // String number_display =Data.substring(0,10);
       // String name_display = Data.substring(10);

        Name_Dis.setText("Name: "+name_display);
        Number_Dis.setText("Phone: "+number_display);
        Email_Dis.setText("Email: "+email_display);

       // if(twitter.isEmpty()){
        if(insta_display.equals("NULL")){ //insta_displa==+
            Insta_Dis.setText("The user has not added Instagram");
            Add_insta.setText("Not Available!");
        }
        else{
           Insta_Dis.setText("Instagram ID @"+insta_display);
            Add_insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   gotoUrl("https://www.twitter.com/"+twitter_display);
                    gotoUrl(Insta_To_Site);
                }
            });
        }

      //  if(linkedin.isEmpty()){
        if(linkedin_display.equals("NULL")){
            LinkedIn_Dis.setText("The user has not added LinkedIn");
            Add_LinkedIn.setText("Not Available!");
        }
        else {
            LinkedIn_Dis.setText("LinkedIn ID @" + linkedin_display);
            Add_LinkedIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //  gotoUrl("https://www.linkedin.com/in/"+linkedin_display);
                    gotoUrl(LinkedIn_To_Site);
                }
            });
        }

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent th = new Intent(ResultDisplay.this, HomeScreen.class);
                startActivity(th);
                finish();
            }
        });

        Rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resc = new Intent(ResultDisplay.this, Scanner.class);
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
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email_display);
                startActivity(intent);
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}