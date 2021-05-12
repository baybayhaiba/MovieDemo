package com.example.kotlinmvvm_7.view.fragment

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kotlinmvvm_7.R
import com.example.kotlinmvvm_7.adapter.AdapterMovie
import com.example.kotlinmvvm_7.databinding.FragmentHomeBinding
import com.example.kotlinmvvm_7.extension.show
import com.example.kotlinmvvm_7.model.Movie
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.utils.Const
import com.example.kotlinmvvm_7.utils.Status
import com.example.kotlinmvvm_7.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterMovies: AdapterMovie
    private lateinit var workManager: WorkManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setupRecyclerView()
        setupData()

        return binding.apply {
            lifecycleOwner = this@HomeFragment
            myViewModel = viewModel
        }.root
    }

    private fun setupData() {
        viewModel.getMovies().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    val result = (it.data as Movie).results!!
                    adapterMovies.setMovies(result)
                    adapterMovies.notifyDataSetChanged()
                    binding.pbMovie.visibility = View.GONE
                }
                Status.LOADING -> binding.pbMovie.visibility = View.VISIBLE
                Status.ERROR -> binding.pbMovie.visibility = View.GONE
            }
        })
    }


    private fun setupRecyclerView() {
        adapterMovies =
            AdapterMovie { handleSaveMovie(it) }
        binding.rcvMovies.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            this.adapter = adapterMovies
            setHasFixedSize(true)
        }
    }

    private fun saveImageToDevice(result: Result) {
        val cw = ContextWrapper(requireActivity())
        val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val path = File(directory, result.backdropPath!!)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(path)
            // Use the compress method on the BitMap object to write image to the OutputStream
            Glide.with(requireActivity())
                .asBitmap()
                .load(Const.BASE_IMAGE.plus(result.backdropPath))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                        fos.let {
                            it.flush()
                            it.close()
                        }
                    }

                    override fun onLoadCleared(placeholddder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
        } catch (e: Exception) {
            requireActivity().show(e.message!!)
        } finally {


        }


    }

    private fun handleSaveMovie(result: Result) {
        //initializer time movie saved
        result.movieSaved = Date().time
        //save in database
        viewModel.saveFavoriteMovie(result)
        //save image to device
        saveImageToDevice(result)
    }


}