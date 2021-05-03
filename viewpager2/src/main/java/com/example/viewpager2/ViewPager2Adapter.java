package com.example.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            default:
                return new HomeFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new MyPageFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
