package com.therzarzayev.retrofitask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.therzarzayev.retrofitask.R
import com.therzarzayev.retrofitask.databinding.ActivityDetailBinding
import com.therzarzayev.retrofitask.utils.Constants.getImg

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val imgPath = intent.getStringExtra("imgPath")
        val movName = intent.getStringExtra("movName")
        val movDesc = intent.getStringExtra("movDesc")
        getImg(this, imgPath.toString(),binding.imageView4)
        binding.detailMovieName.text = movName
        binding.detailMovieDesc.text = movDesc
    }
}