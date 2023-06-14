package com.android.apps.appPrankSound.sound

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.apps.appPrankSound.data.SoundDatabase
import com.android.apps.appPrankSound.data.models.Sound
import com.android.commons.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SoundViewModel : BaseViewModel() {
    private val _sound = MutableLiveData<Sound>().apply {
        value = Sound()
    }
    val sound: LiveData<Sound> = _sound

    fun setSound(sound: Sound) {
        _sound.postValue(sound)
    }

    private val _favouriteSounds = MutableLiveData<MutableList<Sound>>().apply {
        value = ArrayList()
    }
    val favouriteSounds: LiveData<MutableList<Sound>> = _favouriteSounds

    fun saveFavouriteSound(isFavouriteSound: Boolean, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavouriteSound) {
                sound.value?.let {
                    SoundDatabase.getDatabase(context).soundDAO().addFavouriteSound(it)
                    _favouriteSounds.postValue(
                        SoundDatabase.getDatabase(context).soundDAO().getFavouriteSounds()
                    )
                }
            } else {
                sound.value?.let {
                    SoundDatabase.getDatabase(context).soundDAO().deleteFavouriteSound(it)
                    _favouriteSounds.postValue(
                        SoundDatabase.getDatabase(context).soundDAO().getFavouriteSounds()
                    )
                }
            }
        }
    }
}