package com.example.musicdiary;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainSlidePagerAdapter extends FragmentStateAdapter {

    public MainSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch(position)
       {
           case 0: return new SearchFragment();
           case 1: return new FavouritesFragment();
           default: throw new RuntimeException();
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
