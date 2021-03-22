package ru.startandroid.kinopoiskapp3clocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCard extends AppCompatActivity {

    TextView name, age, description;
    ImageView poster;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_card);

        Intent intent = getIntent();
        id = intent.getIntExtra("movieId", 0);

        init();

        Call<Movies> call = ApiClient.getService().getMovieDate(id);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies movie = response.body();
                    name.setText(movie.getName());
                    age.setText(movie.getAge());
                    description.setText(movie.getDescription());

                    Picasso.with(context)
                            .load(PHOTO_URL + movie.getPoster())
                            .into(poster);
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    public void init()
    {
        name = findViewById(R.id.tvMovieName);
        age = findViewById(R.id.tvMovieAge);
        description = findViewById(R.id.tvMovieDescription);
        poster = findViewById(R.id.ivPoster);
    }
}