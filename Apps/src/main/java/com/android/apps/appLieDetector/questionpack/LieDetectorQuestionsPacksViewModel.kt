package com.android.apps.appLieDetector.questionpack

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.commons.base.BaseViewModel
import com.google.gson.Gson
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPacksResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LieDetectorQuestionsPacksViewModel : BaseViewModel() {

    private var questionPack = QuestionPack()

    // data this here equal question packs list
    private val _data = MutableLiveData<MutableList<QuestionPack>>().apply {
        value = ArrayList()
    }
    val data: LiveData<MutableList<QuestionPack>> = _data

    fun getQuestionsPacks(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val json = getJsonFromAssets("app_lie_detector/lie_detector.json", context)
            val response = Gson().fromJson(json, QuestionPacksResponse::class.java)
            _data.postValue(response.questionPacks)
        }
    }

    fun setQuestionPackName(questionPack: QuestionPack) {
        this.questionPack = questionPack
    }

    fun getQuestionPackName(): QuestionPack {
        return this.questionPack
    }
}