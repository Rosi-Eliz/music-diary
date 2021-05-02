package com.example.musicdiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicdiary.networking.Artist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        this.view = view;
        return view;
    }

    public void setContent() {
        SharedPreferences settings;
        recyclerView = view.findViewById(R.id.favourites_recycler_view);
        settings = view.getContext().getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        String currentArtist = settings.getString("artists", "");
        List<Artist> favouriteArtists = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Artist>>(){}.getType();

        favouriteArtists = new Gson().fromJson(currentArtist, listType);
        if(favouriteArtists != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new FavouritesRecyclerViewAdapter(view.getContext(), favouriteArtists));
        }
    }
}