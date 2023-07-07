package com.project.mygithub.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.project.mygithub.DetailUser
import com.project.mygithub.R
import com.project.mygithub.adapter.SectionPagerAdapter
import com.project.mygithub.database.FavoriteEntity
import com.project.mygithub.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("KEY_ID", 0)
        val avatar = intent.getStringExtra("KEY_AVATAR").toString()
        val username = intent.getStringExtra("USERNAME").toString()

        val tabs: TabLayout = binding.tabs
        var isChecked = false

        supportActionBar?.elevation = 0f
        detailViewModel.setDetailData(username)
        detailViewModel.items.observe(this) { Items ->
            setDetailData(Items)
        }
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        detailViewModel.snackbarText.observe(this) {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.getFavoriteById(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    isChecked = if (count > 0) {
                        binding.detailFabFavorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.detailFabFavorite.context,
                                R.drawable.baseline_favorite_24
                            )
                        )
                        true
                    } else {
                        binding.detailFabFavorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.detailFabFavorite.context,
                                R.drawable.baseline_favorite_border_24
                            )
                        )
                        false
                    }
                }
            }
        }

        binding.detailFabFavorite.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                val data = FavoriteEntity(id, username, avatar)
                detailViewModel.insert(data)
                binding.detailFabFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.detailFabFavorite.context,
                        R.drawable.baseline_favorite_24
                    )
                )
            } else {
                val data = FavoriteEntity(id, username, avatar)
                detailViewModel.delete(data)
                binding.detailFabFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.detailFabFavorite.context,
                        R.drawable.baseline_favorite_border_24
                    )
                )
            }
        }

        val viewPager: ViewPager2 = binding.viewPager
        val sectionsPagerAdapter = SectionPagerAdapter(this)
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


    }

    private fun setDetailData(detailUser: DetailUser) {
        with(binding) {
            tvName.text = detailUser.name
            tvUsername.text = buildString {
                append("@ ")
                append(detailUser.login)
            }
            Glide.with(this@DetailActivity)
                .load(detailUser.avatarUrl)
                .circleCrop()
                .into(binding.Profile)
            followers.text = buildString {
                append(detailUser.followers.toString())
                append(" Followers")
            }
            following.text = buildString {
                append(detailUser.following.toString())
                append(" Following")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        var username = String()

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}

