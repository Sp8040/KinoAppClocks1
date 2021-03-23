package ru.startandroid.kinopoiskapp3clocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFavorite extends AppCompatActivity {

    ImageView poster;
    TextView name;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private Context mContext;
    int id;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_favorite);

        init();

        mSettings = getApplicationContext().getSharedPreferences("MoviePrefs", Context.MODE_PRIVATE);


        id = mSettings.getInt("idMovie", 0);

        Call<Movies> call = ApiClient.getService().getMovieDate(id);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies movie = response.body();
                    name.setText(movie.getName());

                    Picasso.with(mContext)
                            .load(PHOTO_URL + movie.getPoster())
                            .resize(150,210)
                            .into(poster);

                }
                else{
                    Toast.makeText(MoviesFavorite.this, response.errorBody().toString(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    private void init() {
        poster = findViewById(R.id.ivFavoritePoster);
        name = findViewById(R.id.tvFavoriteName);

    }
}
