package com.nereoontiveros.popularmovies2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nereoontiveros.popularmovies2.R
import com.nereoontiveros.popularmovies2.model.Movie
import com.nereoontiveros.popularmovies2.utils.MoviesRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MoviesRepository.getPopularMovies(
            onSuccess = {movies-> Log.d("MainActivity", "Movies: $movies")},
            onError = {Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()}
        )
    }

}