package com.example.musicdiary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musicdiary.networking.SearchQuery;
import com.example.musicdiary.networking.WebHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private Context context;
    private TextInputEditText textInputEditText;
    private Button searchButton;
    private RecyclerView recyclerView;


    public SearchFragment(Context context) {
        this.context = context;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        textInputEditText = view.findViewById(R.id.text_input);
        searchButton = view.findViewById(R.id.search_button);
        recyclerView = view.findViewById(R.id.artists_recycler_view);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditText.clearFocus();
                SearchFragment.hideKeyboardFrom(context, view);
                String searchQuery = textInputEditText.getText().toString();

                WebHandler webHandler = new WebHandler();
                webHandler.getArtists(searchQuery, new Callback<SearchQuery>() {
                    @Override
                    public void onResponse(Call<SearchQuery> call, Response<SearchQuery> response) {

                        if(response.body() == null)
                        {
                            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        SearchRecyclerViewAdapter searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(context, response.body());
                        recyclerView.setAdapter(searchRecyclerViewAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    }

                    @Override
                    public void onFailure(Call<SearchQuery> call, Throwable t) {
                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
}