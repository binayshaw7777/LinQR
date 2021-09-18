package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class User_QR extends AppCompatActivity {

    Button back;
    ImageView imageView;
    TextView view;
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
        view = findViewById(R.id.view);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);

        String name=sp.getString("saved_name", "");
        String num=sp.getString("saved_num", "");
        String email=sp.getString("saved_email", "");
        String Insta=sp.getString("saved_insta", "");
        String Linkedin=sp.getString("saved_linkedin", "");
        if(Linkedin.isEmpty()){
            Linkedin="NULL";
        }
        if(Insta.isEmpty()){
            Insta="NULL";
        }

      //  String qr = num+name;
      //  String qr = name+":"+num+":"+email+":"+Twitter+":"+Linkedin;
        String qr = name+":"+num+":"+email+":"+Insta+":"+Linkedin;

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
        qrgEncoder = new QRGEncoder(qr, null, QRGContents.Type.TEXT, dimen);
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
                Intent intent = new Intent(User_QR.this, GenQR.class);
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent x = new Intent(User_QR.this, GenQR.class);
        startActivity(x);
        finishAffinity();
    }
}