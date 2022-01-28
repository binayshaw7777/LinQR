package com.geeeky.linqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class Scanner extends AppCompatActivity{

    CodeScanner codeScanner;
    CodeScannerView scannView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_user_qr);
        scannView = findViewById(R.id.scannerView);
        getSupportActionBar().hide();
        codeScanner = new CodeScanner(this,scannView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final String Rawresult = result.getText();
                        String backup = Rawresult.substring(0,16);
                        if(backup.compareTo("http://instagram")==0){
                            String[] split = Rawresult.split("[\\s?]+");
                            String instagram = split[0];
                            gotoUrl(instagram);
                        }
                        else if(backup.compareTo("https://wa.me/qr")==0){
                            gotoUrl(Rawresult);
                        }
                        else if(backup.compareTo("https://facebook")==0){
                            gotoUrl(Rawresult);
                        }
                        else if(backup.compareTo("https://twitter.")==0){
                            gotoUrl(Rawresult);
                        }
                        else{
                            Intent i = new Intent(Scanner.this, ResultDisplay.class);
                            i.putExtra("decoded", Rawresult);
                            startActivity(i);
                        }
                    }
                });

            }
        });


        scannView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();

    }

    public void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(Scanner.this, "Camera Permission is Required.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();

            }
        }).check();
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void onBackPressed(){
        Intent y = new Intent(Scanner.this, HomeScreen.class);
        startActivity(y);
        finishAffinity();
}
}
