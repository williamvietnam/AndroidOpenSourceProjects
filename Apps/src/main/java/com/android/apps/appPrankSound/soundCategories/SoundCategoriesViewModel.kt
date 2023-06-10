package com.android.apps.appPrankSound.soundCategories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.apps.appPrankSound.models.SoundCategoriesResponse
import com.android.apps.appPrankSound.models.SoundCategory
import com.android.core.base.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SoundCategoriesViewModel : BaseViewModel() {

    private val _data = MutableLiveData<MutableList<SoundCategory>>().apply {
        value = ArrayList()
    }
    val data: LiveData<MutableList<SoundCategory>> = _data

    fun getDataFromAssets(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val soundsCategoriesJson = getJsonFromAssets(
                filename = "app_prank_sounds/sounds.json", context = context
            )
            val soundsCategories = Gson().fromJson(
                soundsCategoriesJson, SoundCategoriesResponse::class.java
            )
            _data.postValue(soundsCategories.soundCategories)
        }
    }
}
