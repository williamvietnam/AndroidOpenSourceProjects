package com.multiapplication.apps.calculator

import androidx.lifecycle.MutableLiveData
import com.multiapplication.base.BaseViewModel

class CalculatorViewModel : BaseViewModel() {

    private var temporaryData: String = ""
    var temporary: String? = null
    val data = MutableLiveData<String>()

    fun getDataDisplay() {
        if (data.value != null) {
            temporaryData = data.value!!
        }
        data.value = temporaryData + temporary
    }

    fun calculatePlus(X: Double, Y: Double): Double {
        return X + Y
    }

    fun calculateMinus(X: Double, Y: Double): Double {
        return X - Y
    }

    fun calculateMultiple(X: Double, Y: Double): Double {
        return X * Y
    }

    fun calculateDivide(X: Double, Y: Double): Double {
        return X / Y
    }
}