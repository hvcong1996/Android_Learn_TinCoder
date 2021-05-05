package com.example.product_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private View viewEndAnimation;
    private ImageView imageViewAnimation;

    private int mCountProduct;

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
        viewEndAnimation = findViewById(R.id.viewEndAnimation);
        imageViewAnimation = findViewById(R.id.imageViewAnimation);
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

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getImageViewAnimation() {
        return imageViewAnimation;
    }

    public void setCountProductInCart(int count){

        mCountProduct = count;

        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.tab_color_product))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();

        // itemPosition: vị trí của tab(0: product / 1: cart / 2: notice)
        ahBottomNavigation.setNotification(notification, 1);
    }

    public int getCountProduct() {
        return mCountProduct;
    }
}