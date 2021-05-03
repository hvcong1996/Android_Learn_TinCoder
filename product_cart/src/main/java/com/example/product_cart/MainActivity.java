package com.example.product_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        InitNavItem();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(viewPagerAdapter);
        // Enable chức năng vuốt để change page
        ahBottomNavigationViewPager.setPagingEnabled(true);

        // Event redirect tab
        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                // Khi tab selected thì set index cho ahBottomNavigationViewPager
                ahBottomNavigationViewPager.setCurrentItem(position);

                return true;
            }
        });

        // Event select bottomNavigation Item khi tab change
        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Khi ahBottomNavigationViewPager change thì set ahBottomNavigation tương ứng
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void InitView(){
        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);
    }

    private void InitNavItem(){
        // Create items
        AHBottomNavigationItem productItem = new AHBottomNavigationItem(R.string.tab_title_product, R.drawable.product, R.color.tab_color_product);
        AHBottomNavigationItem cartItem = new AHBottomNavigationItem(R.string.tab_title_cart, R.drawable.cart, R.color.tab_color_cart);
        AHBottomNavigationItem noticeItem = new AHBottomNavigationItem(R.string.tab_title_notice, R.drawable.notice, R.color.tab_color_notice);

        // Add items
        ahBottomNavigation.addItem(productItem);
        ahBottomNavigation.addItem(cartItem);
        ahBottomNavigation.addItem(noticeItem);
    }
}