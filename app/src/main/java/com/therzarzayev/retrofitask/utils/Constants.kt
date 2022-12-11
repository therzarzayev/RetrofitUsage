package com.therzarzayev.retrofitask.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.therzarzayev.retrofitask.R

object Constants {
    var BaseUrl = "https://api.themoviedb.org/"
    var AppId = "e9d54f5a6873daaca2af214734d187b0"


    fun getImg(context: Context, urlPath: String, image: ImageView){
        val url = "https://image.tmdb.org/t/p/w500$urlPath"
        Glide
            .with(context)
            .load(url)
            .placeholder(R.drawable.film)
            .into(image)
    }
}