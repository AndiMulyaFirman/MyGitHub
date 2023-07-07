package com.project.mygithub.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.mygithub.DetailUser
import com.project.mygithub.database.FavoriteDao
import com.project.mygithub.database.FavoriteDatabase
import com.project.mygithub.database.FavoriteEntity
import com.project.mygithub.datasource.Event
import com.project.mygithub.networking.ApiConfig
import com.project.mygithub.repostory.FavoriteRepostory
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _github = MutableLiveData<DetailUser>()
    val items: LiveData<DetailUser> = _github

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    private val mFavoriteRepository: FavoriteRepostory = FavoriteRepostory(application)
    private val favoriteDatabase: FavoriteDatabase? = FavoriteDatabase.getDatabase(application)
    private var viewModelJob = Job()
    private val favoriteDao: FavoriteDao?

    init {
        favoriteDao = favoriteDatabase?.favoriteDao()
    }

    fun insert(favoriteUser: FavoriteEntity) {
        mFavoriteRepository.insert(favoriteUser)
    }

    fun delete(favoriteUser: FavoriteEntity) {
        mFavoriteRepository.delete(favoriteUser)
    }

    fun getFavoriteById(id: Int) = favoriteDao?.getUserFavoriteById(id)


    fun setDetailData(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUser> {
            override fun onResponse(
                call: Call<DetailUser>,
                response: Response<DetailUser>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _github.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}
