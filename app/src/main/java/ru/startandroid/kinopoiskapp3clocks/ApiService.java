package ru.startandroid.kinopoiskapp3clocks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("movies?filter=new")
    Call<List<Movies>> getMovie();

    @GET("movies/{movieId}")
    Call<Movies> getMovieDate(@Path("movieId") int movieId);

}
