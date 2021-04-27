package com.example.bottom_navigation_bar;

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
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new FavoriteFragment();
                break;
            case 2:
                fragment = new FriendFragment();
                break;
        }
        return fragment;
    }

    // Số lượng của tab
    // Ở đây sẽ có 3 tab là : home, favorite, friend
    @Override
    public int getCount() {
        return 3;
    }
}
