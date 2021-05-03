package com.example.get_videos_from_gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGetVideos;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private List<Video> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
        InitData();

    }

    private void InitView(){
        btnGetVideos = (Button) findViewById(R.id.buttonGetVideos);
        btnGetVideos.setOnClickListener(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewVideo);
    }

    private void InitData(){
        videoAdapter = new VideoAdapter(MainActivity.this);

        // GridLayoutManager(Context, column)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonGetVideos:
                RequestPermission();
                break;
        }
    }

    private void RequestPermission(){
        // Init permissionListener
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                GetVideosFromGallery();
                Toast.makeText(MainActivity.this, "User Accept", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "User Cancel", Toast.LENGTH_SHORT).show();
            }
        };

        // Create TedPermission
        TedPermission.with(MainActivity.this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    private void GetVideosFromGallery(){
        videoList = new ArrayList<>();

        Uri uri;
        Cursor cursor;
        int columnIndexData, thumb;

        String absolutePathOfImage = null;
        String thumbnail = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        // Get videos from MediaStore
        String[] project = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA};

        String orderByType = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, project, null, null, orderByType + "DESC");

        columnIndexData = cursor.getColumnIndexOrThrow((MediaStore.MediaColumns.DATA));
        thumb = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);

        while (cursor.moveToNext()){
            absolutePathOfImage = cursor.getString(columnIndexData);
            thumbnail = cursor.getString(thumb);

            // Init Video
            Video video = new Video(absolutePathOfImage, thumbnail);

            // Add video to videoList
            videoList.add(video);
        }

        // Set videoList for videoAdapter
        videoAdapter.SetVideo(videoList);

        // Set videoAdapter for recyclerView
        recyclerView.setAdapter(videoAdapter);
    }
}