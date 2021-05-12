package com.example.kotlinmvvm_7.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.RequestManager
import com.example.kotlinmvvm_7.R
import com.example.kotlinmvvm_7.databinding.ActivityDetailBinding
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.utils.Const.BASE_IMAGE
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : TransformationAppCompatActivity() {
    @Inject
    lateinit var glide: RequestManager
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        intent.getParcelableExtra<Result>(posterExtraName)
            ?.let {
                binding.apply {
                    glide.load(BASE_IMAGE.plus(it.backdropPath))
                        .fitCenter()
                        .into(imgDetailsMovie)
                    tvDetailsTitle.text = it.title
                    tvDetailsDescription.text = it.overview
                    tvVoteCount.text = "Vote count:${it.voteCount}"

                    it.releaseDate?.let {
                        //convert data from yyyy-mm-dd -> dd/mm/yyyy
                        tvDateRelease.text = it.split("-").reversed().joinToString(separator = "/")
                    }

                    pgvRating.apply {
                        progress = (it.voteAverage!!).toFloat()
                        labelText = "Rating:${(this.progress)}"
                    }
                }
            }
    }

    companion object {
        private const val posterExtraName = "resultExtraName"
        fun startActivity(
            context: Context,
            transformationLayout: TransformationLayout,
            result: Result
        ) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(posterExtraName, result)
            TransformationCompat.startActivity(transformationLayout, intent)
        }
    }
}