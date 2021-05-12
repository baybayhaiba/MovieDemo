package com.example.kotlinmvvm_7.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kotlinmvvm_7.R
import com.example.kotlinmvvm_7.databinding.ItemMovieRoomBinding
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.utils.TimeAgo
import com.example.kotlinmvvm_7.view.DetailActivity
import java.io.File


class AdapterMovieRoom(private val clickListener: (Result) -> Unit) :
    RecyclerView.Adapter<AdapterMovieRoom.MyViewHolder>() {
    var previousTime = SystemClock.elapsedRealtime()
    private val movies = ArrayList<Result>()


    public fun setMovies(movies: List<Result>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }


    inner class MyViewHolder(private val binding: ItemMovieRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result, clickListener: (Result) -> Unit) {

            binding.apply {
                tvTitle.text = result.title
                result.movieSaved?.let {
                    tvMovieSaved.text = TimeAgo.convertTimeAgo(it)
                }

                val context = binding.root.context
                val path: String = context.filesDir.parentFile!!.path.plus("/app_imageDir/")
                val file = File(path, result.backdropPath!!)
                Glide.with(binding.root)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imgMovies)

                imgRemove.setOnClickListener { clickListener(result) }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ItemMovieRoomBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_movie_room, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(movies[position], clickListener)

    override fun getItemCount(): Int = movies.size
}


