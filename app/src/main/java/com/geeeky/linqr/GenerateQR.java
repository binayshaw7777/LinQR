package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import java.io.File;
import java.io.FileOutputStream;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQR extends AppCompatActivity {

    private static final int FILE_SHARE_PERMISSION = 102;
    Button back, save;
    ImageView imageView;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_qr);

        imageView = findViewById(R.id.iv_out);
        back = findViewById(R.id.button);
        save = findViewById(R.id.save);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        SharedPreferences sp1 = getApplicationContext().getSharedPreferences("other_details", Context.MODE_PRIVATE);

        String name=sp.getString("saved_name", "");
        String num=sp.getString("saved_num", "");
        String email=sp.getString("saved_email", "");
        String Insta=sp1.getString("saved_Insta", "");
        String Linkedin=sp1.getString("saved_Li", "");
        String Dis=sp1.getString("saved_Dis", "");
        String Git=sp1.getString("saved_Git", "");
        String Twit=sp1.getString("saved_Twit", "");
        String Fb=sp1.getString("saved_Fb", "");

        if(Linkedin.isEmpty()){
            Linkedin="NULL";
        }
        if(Insta.isEmpty()){
            Insta="NULL";
        }
        if(Dis.isEmpty()){
            Dis="NULL";
        }
        if(Twit.isEmpty()){
            Twit="NULL";
        }
        if(Fb.isEmpty()){
            Fb="NULL";
        }
        if(Git.isEmpty()){
            Git="NULL";
        }

        String qr = name+":"+num+":"+email+":"+Insta+":"+Linkedin+":"+Dis+":"+Twit+":"+Fb+":"+Git;
        String fin = "";

        char[] ch = qr.toCharArray();
        for(char c : ch){
            c+=5;
            fin+=c;
        }

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(fin, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenerateQR.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        shareQrCode();
                    }
                    else{
                        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,FILE_SHARE_PERMISSION);
                    }
                }
                else{
                    shareQrCode();
                }
            }

            private void shareQrCode() {
                MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, name+"'s LinQR Code"
                        , null);
                Toast.makeText(GenerateQR.this, "Successfully saved to gallery", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private boolean checkPermission(String permission) {
        int result= ContextCompat.checkSelfPermission(GenerateQR.this,permission);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }
    private void requestPermission(String permission1,int code){
        if(ActivityCompat.shouldShowRequestPermissionRationale(GenerateQR.this,permission1)){

        }
        else{
            ActivityCompat.requestPermissions(GenerateQR.this,new String[]{permission1},code);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent x = new Intent(GenerateQR.this, HomeScreen.class);
        startActivity(x);
        finishAffinity();
    }
}