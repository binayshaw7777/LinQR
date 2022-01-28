package com.geeeky.linqr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultDisplay extends AppCompatActivity {

    TextView Name_Dis, Number_Dis, Email_Dis;
    CardView Add_insta, Add_LinkedIn, Add_Facebook, Add_Twitter, Add_Discord, Add_Github;
    Button Rescan, Add, toHome;

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
        Add_insta = findViewById(R.id.instagram);
        Add_LinkedIn = findViewById(R.id.Linkedin);
        Add_Discord = findViewById(R.id.Discord);
        Add_Facebook = findViewById(R.id.Facebook);
        Add_Github = findViewById(R.id.Github);
        Add_Twitter = findViewById(R.id.Twitter);

        Intent newInt = getIntent();
        String num = newInt.getExtras().getString("decoded");
        char[] dec = num.toCharArray();
        String decrypt = "";
        for(char c : dec){
            c-=5;
            decrypt+=c;
        }

        String[] split = decrypt.split(":");
        String name_display = split[0]; //name
        String number_display = split[1]; //number
        String email_display = split[2]; //email
        String insta = split[3]; //insta
        String linkedin = split[4]; //linkedin
        String discord = split[5]; //Discord
        String twitter = split[6]; //Twitter
        String fb = split[7]; //Facebook
        String git = split[8]; //Github



        String Insta_To_Site = "https://www.instagram.com/"+insta;
        String LinkedIn_To_Site = "https://www.linkedin.com/in/"+linkedin;
        String Discord_To_Site = "https://discordapp.com/users/"+discord;
        String Twitter_To_Site = "https://twitter.com/"+twitter;
        String GitHub_To_Site = "https://www.github.com/"+git;
        String Facebook_To_Site = "https://www.facebook.com/"+fb;

        Name_Dis.setText("Name: "+name_display);
        Number_Dis.setText("Phone: "+number_display);
        Email_Dis.setText("Email: "+email_display);

        if(insta.equals("NULL")){
            Add_insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ResultDisplay.this, "Instagram was not Added by user!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Add_insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl(Insta_To_Site);
                }
            });
        }

        if(discord.equals("NULL")){
            Add_Discord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ResultDisplay.this, "Discord was not Added by user!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Add_Discord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl(Discord_To_Site);
                }
            });
        }

        if(fb.equals("NULL")){
            Add_Facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ResultDisplay.this, "Facebook was not Added by user!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Add_Facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl(Facebook_To_Site);
                }
            });
        }

        if(git.equals("NULL")){
            Add_Github.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ResultDisplay.this, "Github was not Added by user!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Add_Github.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl(GitHub_To_Site);
                }
            });
        }

        if(twitter.equals("NULL")){
            Add_Twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ResultDisplay.this, "Twitter was not Added by user!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Add_Twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl(Twitter_To_Site);
                }
            });
        }

        if(linkedin.equals("NULL")){
            Add_LinkedIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ResultDisplay.this, "LinkedIn was not added by the user!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Add_LinkedIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Back to Home");
        alertDialogBuilder.setIcon(R.drawable.logo);
        alertDialogBuilder.setMessage("Go to Homepage?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent z = new Intent(ResultDisplay.this, HomeScreen.class);
                startActivity(z);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ResultDisplay.this, "Exit cancelled", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }
}