package com.project.mygithub.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.mygithub.ui.main.MainViewModel

class DetailViewModelFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: DetailViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): DetailViewModelFactory {
            if (INSTANCE == null) {
                synchronized(DetailViewModelFactory::class.java) {
                    INSTANCE = DetailViewModelFactory(application)
                }
            }
            return INSTANCE as DetailViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return DetailViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}