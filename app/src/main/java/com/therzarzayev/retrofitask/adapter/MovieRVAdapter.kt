package com.therzarzayev.retrofitask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.therzarzayev.retrofitask.R
import com.therzarzayev.retrofitask.model.MovieModel
import com.therzarzayev.retrofitask.utils.Constants.getImg

class MovieRVAdapter(private val movieList: ArrayList<MovieModel>, private val listener: Listener) :
    RecyclerView.Adapter<MovieRVAdapter.CardViewHolder>() {

    interface Listener {
        fun onItemClick(model: MovieModel)
    }

    class CardViewHolder(itemView: View) : ViewHolder(itemView) {
        val movieName: TextView
        val movieImage: ImageView
        val movieRate: TextView
        val movieLanguage: TextView
        val movieDate: TextView

        init {
            movieName = itemView.findViewById(R.id.filmName)
            movieRate = itemView.findViewById(R.id.filmRate)
            movieImage = itemView.findViewById(R.id.filmImg)
            movieLanguage = itemView.findViewById(R.id.filmLang)
            movieDate = itemView.findViewById(R.id.filmDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieName.text = movie.movieName
        holder.movieRate.text = movie.movieRate
        holder.movieLanguage.text = movie.movieLanguage
        holder.movieDate.text = movie.movieDate
        holder.itemView.setOnClickListener {
            listener.onItemClick(movie)
        }
        getImg(holder.itemView.context, movie.movieImagePath, holder.movieImage)
//        Glide
//            .with(holder.itemView.context)
//            .load(url)
//            .placeholder(R.drawable.film)
//            .into(holder.movieImage)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}