package com.example.bottom_navigation_bar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        SetupViewPager();

        // Event when click item in Navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                    default:
                        // Set Fragment item cho ViewPage
                        // 0: Fragment Home
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_favorite:
                        // Set Fragment item cho ViewPage
                        // 1: Fragment Favorite
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_friend:
                        // Set Fragment item cho ViewPage
                        // 2: Fragment Friend
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }

    private void InitView(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void SetupViewPager(){
        // Init viewPagerAdapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        // Set adapter
        viewPager.setAdapter(viewPagerAdapter);

        // Event khi change Page in ViewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Khi change Page in ViewPager thì sẽ cheked action bar tương ứng
                switch (position){
                    case 0:
                    default:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_friend).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}