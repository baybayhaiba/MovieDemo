package com.example.kotlinmvvm_7.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kotlinmvvm_7.R
import com.example.kotlinmvvm_7.databinding.ItemMovieBinding
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.utils.Const.BASE_IMAGE
import com.example.kotlinmvvm_7.view.DetailActivity

class AdapterMovie(private val clickListener: (Result) -> Unit) :
    RecyclerView.Adapter<AdapterMovie.MyViewHolder>() {
    var previousTime = SystemClock.elapsedRealtime()


    private val movies = ArrayList<Result>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(movies[position], clickListener)

    override fun getItemCount(): Int = movies.size

    public fun setMovies(movies: List<Result>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }


    inner class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result, clickListener: (Result) -> Unit) {

            binding.apply {
                tvTitle.text = result.title

                Glide.with(binding.root)
                    .load(BASE_IMAGE.plus(result.backdropPath))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imgMovies)


                imgFavorite.setOnClickListener { clickListener(result) }

                root.setOnClickListener {
                    val now = SystemClock.elapsedRealtime()
                    if (now - previousTime >= itemPosterTransformationLayout.duration) {
                        DetailActivity.startActivity(
                            root.context,
                            itemPosterTransformationLayout,
                            result
                        )
                        previousTime = now
                    }
                }
            }

        }
    }


}


