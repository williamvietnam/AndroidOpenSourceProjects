package com.android.apps.appWAStickers.screens.stickers

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.apps.appWAStickers.models.StickerPack
import com.android.apps.appWAStickers.models.StickersResponse
import com.android.commons.base.BaseViewModel
import com.android.apps.appWAStickers.core.WhitelistCheck
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WAStickersViewModel : BaseViewModel() {
    private val stickersData = MutableLiveData<ArrayList<StickerPack>>().apply {
        value = ArrayList()
    }

    val getStickersData: MutableLiveData<ArrayList<StickerPack>> = stickersData
    var stickerPackData: StickerPack? = null

    fun loadData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val json = getJsonFromAssets(
                filename = "stickerswhatsapp.json",
                context
            )
            val response = Gson().fromJson(json, StickersResponse::class.java)
            val data = response.stickerCategories
            if (data != null) {
                for (i in 0 until data.size) {
                    data[i].isWhitelisted = WhitelistCheck.isWhitelisted(
                        context = context, identifier = data[i].identifier!!
                    )
                }
            }
            stickersData.postValue(data)
        }
    }
}