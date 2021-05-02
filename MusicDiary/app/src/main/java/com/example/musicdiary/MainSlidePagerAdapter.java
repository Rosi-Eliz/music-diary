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
    private FavouritesFragment favouritesFragment;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch(position)
       {
           case 0: {
               return new SearchFragment(context);
           }
           case 1: {
               favouritesFragment = new FavouritesFragment();
           return  favouritesFragment;

           }
           default: throw new RuntimeException();
       }
    }

    public void invalidateFavouritesFragment() {
        if(favouritesFragment != null) {
            favouritesFragment.setContent();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
