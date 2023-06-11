package com.android.apps.appPrankSound.sounds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.apps.appPrankSound.data.models.SoundCategory
import com.android.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SoundsViewModel : BaseViewModel() {
    private val _data = MutableLiveData<SoundCategory>().apply {
        value = SoundCategory()
    }
    val data: LiveData<SoundCategory> = _data

    fun setSoundCategory(soundCategory: SoundCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.postValue(soundCategory)
        }
    }
}