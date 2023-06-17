package com.android.apps.appWAStickers.screens.stickerDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.apps.appWAStickers.models.Sticker
import com.android.apps.appWAStickers.models.StickerPack
import com.android.commons.base.BaseViewModel

class WAStickerDetailViewModel: BaseViewModel() {
    private val stickersData = MutableLiveData<ArrayList<Sticker>>().apply {
        value = ArrayList()
    }
    private val stickerPackData = MutableLiveData<StickerPack>().apply {
        value = StickerPack()
    }
    val getStickersData: LiveData<ArrayList<Sticker>> = stickersData
    val getStickerPackData: LiveData<StickerPack> = stickerPackData

    var positionStartWhenScreenIsCalled: Int = 0
    var dataSize: Int = 0

    fun receiveData(stickerPack: StickerPack) {
        stickersData.postValue(stickerPack.stickers as ArrayList<Sticker>?)
        stickerPackData.postValue(stickerPack)
    }
}