package com.project.mygithub.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.mygithub.database.FavoriteDao
import com.project.mygithub.database.FavoriteDatabase
import com.project.mygithub.database.FavoriteEntity

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var favoriteDao: FavoriteDao?
    private var favoriteDatabase: FavoriteDatabase? = FavoriteDatabase.getDatabase(application)

    init {
        favoriteDao = favoriteDatabase?.favoriteDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteEntity>>? = favoriteDao?.getAllFavoriteUsers()
}
