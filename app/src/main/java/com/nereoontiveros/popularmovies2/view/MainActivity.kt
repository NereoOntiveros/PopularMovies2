package com.nereoontiveros.popularmovies2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nereoontiveros.popularmovies2.R
import com.nereoontiveros.popularmovies2.model.Movie
import com.nereoontiveros.popularmovies2.utils.MoviesRepository

class MainActivity : AppCompatActivity() {

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager

    private var popularMoviesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularMovies = findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf())
        popularMovies.adapter = popularMoviesAdapter
        getPopularMovies()

        MoviesRepository.getPopularMovies(
            popularMoviesPage,
             ::onPopularMoviesFetched,
             ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }
    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
    private fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }
    private fun attachPopularMoviesOnScrollListener() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //the total number of movies inside popularMoviesAdapter. will be encreasing
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                //the current number of child views attached to the RecyclerView currently being recycled
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                //the position fo the leftmost visible item in the list
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                //the condition will be true if the user scrolls past halfway plus buffered value of visibleItemCount
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)//disable scroll listener
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }



}