package com.project.mygithub.repostory

import android.app.Application
import com.project.mygithub.database.FavoriteDao
import com.project.mygithub.database.FavoriteDatabase
import com.project.mygithub.database.FavoriteEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class FavoriteRepostory(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun insert(favoriteUser: FavoriteEntity) {
        executorService.execute { mFavoriteDao.insert(favoriteUser) }
    }

    fun delete(favoriteUser: FavoriteEntity) {
        executorService.execute { mFavoriteDao.removeFav(favoriteUser.id) }
    }
}