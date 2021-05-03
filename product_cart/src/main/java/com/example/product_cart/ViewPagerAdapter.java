package com.example.product_cart;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
            default:
                fragment = new ProductFragment();
                break;
            case 1:
                fragment= new CartFragment();
                break;
            case 2:
                fragment = new NoticeFragment();
                break;
        }
        return fragment;
    }

    // 3 Tab
    @Override
    public int getCount() {
        return 3;
    }
}
