package com.project.mygithub.ui.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mygithub.adapter.FavoriteAdapter
import com.project.mygithub.database.FavoriteEntity
import com.project.mygithub.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavorite.addItemDecoration(itemDecoration)

        favoriteViewModel.getFavoriteUser()?.observe(this) { listFav ->
            adapter.setListFavorite(listFav as ArrayList<FavoriteEntity>)
            binding.rvFavorite.adapter = adapter
        }


    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteUser()

        favoriteViewModel.getFavoriteUser()?.observe(this) { listFav ->
            adapter.setListFavorite(listFav as ArrayList<FavoriteEntity>)
            binding.rvFavorite.adapter = adapter
        }
    }

}