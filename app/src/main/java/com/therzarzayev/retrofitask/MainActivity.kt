package com.therzarzayev.retrofitask

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.therzarzayev.retrofitask.adapter.MovieRVAdapter
import com.therzarzayev.retrofitask.databinding.ActivityMainBinding
import com.therzarzayev.retrofitask.model.MovieModel
import com.therzarzayev.retrofitask.response.MovieResponse
import com.therzarzayev.retrofitask.service.ApiInterface
import com.therzarzayev.retrofitask.service.RetrofitClient
import com.therzarzayev.retrofitask.ui.DetailActivity
import com.therzarzayev.retrofitask.utils.Constants.AppId
import com.therzarzayev.retrofitask.utils.Constants.BaseUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), MovieRVAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieList: ArrayList<MovieModel>
    private lateinit var adapter: MovieRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieList = ArrayList()
        val layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        binding.rv.layoutManager = layoutManager
        binding.rv.setHasFixedSize(true)
        getMovieData()

    }

    private fun getMovieData() {
        val service = RetrofitClient.retrofit(BaseUrl).create(ApiInterface::class.java)
        val call = service.getMovieDataService(AppId)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val res = response.body()

                    res?.items?.forEach {
                        val movie = MovieModel(
                            it.title,
                            it.voteAverage.toString(),
                            it.posterPath,
                            it.originalLanguage,
                            it.releaseDate,
                            it.overview
                        )
                        movieList.add(movie)
                    }
                    adapter = MovieRVAdapter(movieList,this@MainActivity)
                    binding.rv.adapter = adapter
                }
            }


            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println(t.message)
            }
        })
    }

    override fun onItemClick(model: MovieModel) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("imgPath",model.movieImagePath)
        intent.putExtra("movName",model.movieName)
        intent.putExtra("movDesc",model.movieDesc)
        startActivity(intent)
    }
}