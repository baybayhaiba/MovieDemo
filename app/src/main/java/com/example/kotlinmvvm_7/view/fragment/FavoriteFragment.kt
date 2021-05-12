package com.example.kotlinmvvm_7.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvvm_7.R
import com.example.kotlinmvvm_7.adapter.AdapterMovieRoom
import com.example.kotlinmvvm_7.databinding.FragmentFavoriteBinding
import com.example.kotlinmvvm_7.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapterMovies: AdapterMovieRoom


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        setupData()
        return binding.root
    }


    private fun setupData() {
        adapterMovies = AdapterMovieRoom { viewModel.deleteMovieSaved(it) }
        viewModel.movies.observe(viewLifecycleOwner, {
            adapterMovies.setMovies(it)
            adapterMovies.notifyDataSetChanged()
        })
        binding.rcvMoviesFavorite.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            this.adapter = adapterMovies
            setHasFixedSize(true)
        }
    }

}