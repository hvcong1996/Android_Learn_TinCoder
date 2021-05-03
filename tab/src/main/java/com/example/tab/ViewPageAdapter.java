package com.example.tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
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
                fragment = new MyPageFragment();
        }

        return fragment;
    }

    // Có 3 tab nên sẽ return 3 tab
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";

        switch (position){
            case 0:
            default:
                title = "Home";
                break;
            case 1:
                title = "Favorite";
                break;
            case 2:
                title = "My Page";
                break;
        }

        return title;
    }
}
