package com.example.musicdiary;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainSlidePagerAdapter extends FragmentStateAdapter {
    private Context context;

    public MainSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch(position)
       {
           case 0: return new SearchFragment(context);
           case 1: return new FavouritesFragment();
           default: throw new RuntimeException();
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
