package com.project.mygithub.database


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavoriteUsers(): LiveData<List<FavoriteEntity>>

    @Query("SELECT count(*) FROM favorite WHERE id = :id")
    fun getUserFavoriteById(id: Int): Int

    @Insert
    fun insert(favoriteUser: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :id")
    fun removeFav(id: Int?): Int
}