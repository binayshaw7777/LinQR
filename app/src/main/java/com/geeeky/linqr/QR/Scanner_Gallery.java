package com.geeeky.linqr.QR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geeeky.linqr.Main.HomeScreen;
import com.geeeky.linqr.R;
import com.geeeky.linqr.Main.ResultDisplay;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Scanner_Gallery extends AppCompatActivity {

    private Button home, con;
    private TextView Soc, Lin;
    private ImageView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scanner_gallery);

        home = findViewById(R.id.home);
        con = findViewById(R.id.connect);
        Soc = findViewById(R.id.name);
        Lin = findViewById(R.id.link);
        change = findViewById(R.id.imageView5);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Scanner_Gallery.this, HomeScreen.class);
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
                    int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
                    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());

                    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    MultiFormatReader reader = new MultiFormatReader();
                    Result result = ((MultiFormatReader) reader).decode(bitmap);

                    final String Rawresult = result.getText();
                    String backup = Rawresult.substring(0, 16);
                    if (backup.compareTo("http://instagram") == 0) {
                        String[] split = Rawresult.split("[\\s?]+");
                        String instagram = split[0];
                        gotoUrl(instagram);
                        Soc.setText("Instagram Found");
                        change.setBackgroundResource(R.drawable.instagram_high);
                        Lin.setText(result.getText());
                        con.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotoUrl(Rawresult);
                            }
                        });
                    } else if (backup.compareTo("https://wa.me/qr") == 0) {
                        gotoUrl(Rawresult);
                        Soc.setText("WhatsApp Found");
                        change.setBackgroundResource(R.drawable.whatsapp);
                        Lin.setText(result.getText());
                        con.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotoUrl(Rawresult);
                            }
                        });
                    } else if (backup.compareTo("https://facebook") == 0) {
                        gotoUrl(Rawresult);
                        Soc.setText("Facebook Found");
                        change.setBackgroundResource(R.drawable.facebook_high);
                        Lin.setText(result.getText());
                        con.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotoUrl(Rawresult);
                            }
                        });
                    } else if (backup.compareTo("https://twitter.") == 0) {
                        gotoUrl(Rawresult);
                        Soc.setText("Twitter Found");
                        change.setBackgroundResource(R.drawable.twitter_high);
                        Lin.setText(result.getText());
                        con.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotoUrl(Rawresult);
                            }
                        });
                    } else {
                        Intent i = new Intent(Scanner_Gallery.this, ResultDisplay.class);
                        i.putExtra("decoded", Rawresult);
                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Scanner_Gallery.this, "Selected Image is not a QR Code!", Toast.LENGTH_SHORT).show();
                    Intent inty = new Intent(Scanner_Gallery.this, HomeScreen.class);
                    startActivity(inty);
                    finishAffinity();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Toast.makeText(Scanner_Gallery.this, "Something went wrong", Toast.LENGTH_LONG).show();
                Intent intx = new Intent(Scanner_Gallery.this, HomeScreen.class);
                startActivity(intx);
                finishAffinity();
            }

        } else {
            Toast.makeText(Scanner_Gallery.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            Intent intz = new Intent(Scanner_Gallery.this, HomeScreen.class);
            startActivity(intz);
            finishAffinity();
        }
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}