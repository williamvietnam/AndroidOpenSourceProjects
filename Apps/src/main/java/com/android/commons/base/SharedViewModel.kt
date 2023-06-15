package com.android.commons.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : BaseViewModel() {
    private val _questionPack = MutableLiveData<QuestionPack>().apply {
        value = QuestionPack()
    }

    val questionPack: LiveData<QuestionPack> = _questionPack

    fun setQuestionPack(questionPack: QuestionPack) {
        viewModelScope.launch(Dispatchers.IO) {
            _questionPack.postValue(questionPack)
        }
    }
}