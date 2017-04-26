package com.mardyoe.smstodb.controller.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mardyoe.smstodb.R;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 23){
            requestContactPermission();
        }

        Toast.makeText(this, "No UI to show. Sorry", Toast.LENGTH_SHORT).show();
    }

    private void requestContactPermission() {

        int hasContactPermission =ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if (hasContactPermission != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]   {Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_ASK_PERMISSIONS);
        }else {
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Permission", "Contact permission has now been granted. Showing result.");
                    Toast.makeText(this,"Contact Permission is Granted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.i("Permission", "Contact permission was NOT granted.");
                    finish();
                }
                break;
        }
    }
}
