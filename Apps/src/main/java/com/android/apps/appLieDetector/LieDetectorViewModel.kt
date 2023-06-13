package com.android.apps.appLieDetector

import com.android.core.base.BaseViewModel
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack
import java.util.Random

class LieDetectorViewModel: BaseViewModel() {
    // data this here equal a question pack
    private var data: QuestionPack? = null

    fun setData(data: QuestionPack) {
        this.data = data
    }

    fun getData(): QuestionPack? {
        return data
    }

    fun getRandomBoolean(): Boolean {
        return Random().nextBoolean()
    }
}