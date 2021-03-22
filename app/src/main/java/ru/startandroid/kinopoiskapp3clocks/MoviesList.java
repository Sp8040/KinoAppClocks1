package ru.startandroid.kinopoiskapp3clocks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesList extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerViewHor;

    List<Movies> mMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        mMovies = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.rvMainMovies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        MoviesAdapter moviesAdapter = new MoviesAdapter(mMovies);
        mRecyclerView.setAdapter(moviesAdapter);


        final Call<List<Movies>> call = ApiClient.getService().getMovie();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                if (response.isSuccessful()) {
                    mMovies.addAll(response.body());
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Toast.makeText(MoviesList.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Toast.makeText(MoviesList.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
