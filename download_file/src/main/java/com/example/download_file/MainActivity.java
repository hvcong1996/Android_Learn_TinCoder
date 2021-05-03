package com.example.download_file;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 900;
    private EditText editTextUrl;
    private Button buttonDownload;

    private static final String URL_FILE = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
            }
        });
    }

    private void InitView(){
        editTextUrl = findViewById(R.id.editTextUrl);
        buttonDownload = findViewById(R.id.buttonDownload);

        editTextUrl.setText(URL_FILE);
    }

    private void CheckPermission(){

        // Android 6(API 23) thì cần phải có request permission khi sử dụng
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Kiểm tra permission đã được cho phép trước đó chưa để request
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

                // requestPermissions(Các permission cần request, Request permission code)
                requestPermissions(permission, REQUEST_PERMISSION_CODE);
            }
            else {
                StartDownload();
            }
        }
        else {
            StartDownload();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                StartDownload();
            }
            else {
                Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void StartDownload() {
        String urlFile = editTextUrl.getText().toString().trim();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlFile));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        // Download notification
        request.setTitle("Download");
        request.setDescription("Downloading...");
        // Set visibility
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // Set location in device to download file
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, String.valueOf(System.currentTimeMillis()));

        // Init downloadManager
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        if(downloadManager != null){
            downloadManager.enqueue(request);
        }
    }
}