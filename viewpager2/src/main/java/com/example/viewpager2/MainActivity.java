package com.example.viewpager2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    private RadioGroup radioGroup1, radioGroup2, radioGroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        //
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(MainActivity.this);
        viewPager2.setAdapter(viewPager2Adapter);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonLeftToRight:
                    default:
                        viewPager2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                        break;
                    case R.id.radioButtonRightToLeft:
                        viewPager2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonVertical:
                    default:
                        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                        break;
                    case R.id.radioButtonHorizontal:
                        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        break;
                }
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonNormal:
                        viewPager2.setPageTransformer(null);
                        break;
                    case R.id.radioButtonZoomOut:
                        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
                        break;
                    case R.id.radioButtonDepth:
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                }
            }
        });
    }

    private void InitView(){
        viewPager2 = findViewById(R.id.viewPager2);

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);

        radioGroup1.check(R.id.radioButtonLeftToRight);
        radioGroup2.check(R.id.radioButtonHorizontal);
        radioGroup3.check(R.id.radioButtonNormal);
    }
}