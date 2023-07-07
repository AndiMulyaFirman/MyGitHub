package com.project.mygithub.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.mygithub.GitResponse
import com.project.mygithub.ItemsItem
import com.project.mygithub.datasource.Event
import com.project.mygithub.networking.ApiConfig
import com.project.mygithub.ui.setting.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val pref: SettingPreferences) : ViewModel() {

    private val _github = MutableLiveData<List<ItemsItem>>()
    val items: LiveData<List<ItemsItem>> = _github

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun findUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsername(username)
        client.enqueue(object : Callback<GitResponse> {
            override fun onResponse(
                call: Call<GitResponse>,
                response: Response<GitResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _github.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}