package com.example.slide_image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> photoList;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        InitData();

        AutoSlideImage();
    }

    private void InitView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager_Slide);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);
    }

    private void InitData(){
        photoList = GetPhotoList();

        photoAdapter = new PhotoAdapter(MainActivity.this, photoList);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);

        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private List<Photo> GetPhotoList(){
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo(R.drawable.img1));
        photos.add(new Photo(R.drawable.img2));
        photos.add(new Photo(R.drawable.img3));
        photos.add(new Photo(R.drawable.img4));
        photos.add(new Photo(R.drawable.img5));

        return photos;
    }

    private void AutoSlideImage(){
        // Check data valid
        if(viewPager == null || photoList == null || photoList.isEmpty()){
            return;
        }

        // Init timer
        if(timer == null){
            timer = new Timer();
        }

        // delay: Thời gian delay để qua xử lý tiếp theo
        // period: Thời gian cho 1 xử lý
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // Index of image
                        int currentItem = viewPager.getCurrentItem();
                        // Total of image
                        int totalItem = photoList.size() - 1;

                        // Plus currentItem
                        if(currentItem < totalItem){
                            currentItem ++;

                            // Set to viewPager
                            viewPager.setCurrentItem(currentItem);
                        }
                        // Reset viewPager
                        else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    // If activity destroy then clear timer
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
}