package ru.startandroid.kinopoiskapp3clocks;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private List<Movies> mMovies;
    private Context mContext;
    private SharedPreferences mSettings;

    public MoviesAdapter(List<Movies> movies) {
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movies movie = mMovies.get(position);
        Picasso.with(mContext)
                .load(PHOTO_URL + movie.getPoster())
                .resize(150,210)
                .into(holder.posterImageView);

        holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favoriteImageView.setImageResource(R.drawable.breakherat);

                mSettings = v.getContext().getSharedPreferences("MoviePrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt("idMovie", movie.getMovieId());
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }
        return mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView, favoriteImageView, deleteImageView;

        ViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.ivPoster);
            favoriteImageView = (ImageView) itemView.findViewById(R.id.ivAddFavorite);
            deleteImageView = (ImageView) itemView.findViewById(R.id.ivDelete);
        }
    }
}
