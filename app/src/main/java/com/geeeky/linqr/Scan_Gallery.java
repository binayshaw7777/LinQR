package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Scan_Gallery extends AppCompatActivity {

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scan_gallery);

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Scan_Gallery.this, HomeScreen.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1000);


    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {

        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            try {

                final Uri imageUri = data.getData();

                final InputStream imageStream = getContentResolver().openInputStream(imageUri);

                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                try {

                    Bitmap bMap = selectedImage;

                    String contents = null;



                    int[] intArray = new int[bMap.getWidth()*bMap.getHeight()];

                    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());



                    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray);

                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));



                    MultiFormatReader reader = new MultiFormatReader();

                    Result result = ((MultiFormatReader) reader).decode(bitmap);

                   // contents = result.getText();
                    final String Rawresult = result.getText();
                    Intent i = new Intent(Scan_Gallery.this, ResultDisplay.class);
                    i.putExtra("decoded", Rawresult);
                    startActivity(i);

                  //  Toast.makeText(getApplicationContext(),contents,Toast.LENGTH_LONG).show();



                }catch (Exception e){

                    e.printStackTrace();

                }

                //  image_view.setImageBitmap(selectedImage);

                //   } catch (FileNotFoundException | FileNotFoundException e) {
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Toast.makeText(Scan_Gallery.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }

        }else {

            Toast.makeText(Scan_Gallery.this, "You haven't picked Image",Toast.LENGTH_LONG).show();

        }


    }
}