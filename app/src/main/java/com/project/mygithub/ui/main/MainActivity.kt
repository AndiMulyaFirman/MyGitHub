package com.project.mygithub.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.mygithub.ItemsItem
import com.project.mygithub.R
import com.project.mygithub.adapter.MainAdapter
import com.project.mygithub.databinding.ActivityMainBinding
import com.project.mygithub.ui.favorite.FavoriteActivity
import com.project.mygithub.ui.setting.SettingActivity
import com.project.mygithub.ui.setting.SettingPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = getViewModel()
        darkModeCheck()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvItem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvItem.addItemDecoration(itemDecoration)

        supportActionBar?.apply {
            title = "Github User's Search"
        }

        mainViewModel.items.observe(this) { Items ->
            setItemData(Items)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.snackbarText.observe(this) {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getViewModel(): MainViewModel {
        val pref = SettingPreferences.getInstance(dataStore)
        return ViewModelProvider(this, MainViewModelFactory(pref))[MainViewModel::class.java]
    }

    private fun darkModeCheck() {
        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive ->
            val mode =
                if (isDarkModeActive) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                mainViewModel.findUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    private fun setItemData(Items: List<ItemsItem>) {
        val adapterMain = MainAdapter(Items)
        binding.rvItem.adapter = adapterMain
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.btn_setting -> {
                val setting = Intent(this, SettingActivity::class.java)
                startActivity(setting)
                true
            }
            R.id.btn_favorite -> {
                val favorite = Intent(this, FavoriteActivity::class.java)
                startActivity(favorite)
                true
            }
            else -> false
        }

        return super.onOptionsItemSelected(item)
    }

}