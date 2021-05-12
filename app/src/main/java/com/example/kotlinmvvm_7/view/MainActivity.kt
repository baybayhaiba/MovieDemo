package com.example.kotlinmvvm_7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.kotlinmvvm_7.R
import com.example.kotlinmvvm_7.databinding.ActivityMainBinding
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.view.fragment.FavoriteFragment
import com.example.kotlinmvvm_7.view.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragments: ArrayList<Fragment>
    private lateinit var active: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState == null) {
            fragments = arrayListOf(HomeFragment(), FavoriteFragment())
            setFragment()
            onClickBottom()
        }

    }

    private fun setFragment() {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, fragments[0].apply {
                active = this
            }, "Home")
            .commit()


        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, fragments[1], "Favorite")
            .hide(fragments[1])
            .commit()
    }

    private fun onClickBottom() {
        binding.bnvMovie.setOnNavigationItemSelectedListener {
            active = when (it.itemId) {
                R.id.mnHome -> {
                    supportFragmentManager.beginTransaction()
                        .hide(active).show(fragments[0]).commit()
                    fragments[0]
                }
                R.id.mnFavorite -> {
                    supportFragmentManager.beginTransaction()
                        .hide(active).show(fragments[1]).commit()
                    fragments[1]
                }
                else -> throw IllegalArgumentException("Dont find fragment")
            }
            true
        }
    }

}