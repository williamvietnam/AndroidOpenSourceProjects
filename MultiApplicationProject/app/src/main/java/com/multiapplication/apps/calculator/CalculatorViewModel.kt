package com.multiapplication.apps.calculator

import androidx.lifecycle.MutableLiveData
import com.multiapplication.base.BaseViewModel

class CalculatorViewModel : BaseViewModel() {

    var typeButton: String = ""
    var isEnablePlusButton: Boolean = false
    var isEnableMinusButton: Boolean = false
    var isEnableMultipleButton: Boolean = false
    var isEnableDivideButton: Boolean = false
    private var temporaryData: String = ""
    var temporary: String? = null
    val data = MutableLiveData<String>()
    private var storeData: String? = null

    fun getDataDisplay() {
        if (data.value != null) {
            temporaryData = data.value!!
        }
        data.value = temporaryData + temporary

        if ((isEnablePlusButton || isEnableMinusButton || isEnableMultipleButton || isEnableDivideButton) && (storeData == null)) {
            if (data.value != null) {
                storeData = temporaryData
                data.value = temporary

                isEnablePlusButton = false
                isEnableMinusButton = false
                isEnableMultipleButton = false
                isEnableDivideButton = false
            }
        }
    }

    fun getCalculationResultWhenClickCalculationButton() {
        if (storeData != null && data.value != null) {
            when (typeButton) {
                CalculatorConstants.PLUS -> {
                    data.value = this.calculatePlus(storeData!!, data.value!!)
                }
                CalculatorConstants.MINUS -> {
                    data.value = this.calculateMinus(storeData!!, data.value!!)
                }
                CalculatorConstants.MULTIPLE -> {
                    data.value = this.calculateMultiple(storeData!!, data.value!!)
                }
                CalculatorConstants.DIVIDE -> {
                    data.value = this.calculateDivide(storeData!!, data.value!!)
                }
            }
        }
    }

    fun getCalculationResultWhenClickEqualButton() {
        if (storeData != null && data.value != null) {
            when (typeButton) {
                CalculatorConstants.PLUS -> {
                    data.value = this.calculatePlus(storeData!!, data.value!!)
                }
                CalculatorConstants.MINUS -> {
                    data.value = this.calculateMinus(storeData!!, data.value!!)
                }
                CalculatorConstants.MULTIPLE -> {
                    data.value = this.calculateMultiple(storeData!!, data.value!!)
                }
                CalculatorConstants.DIVIDE -> {
                    data.value = this.calculateDivide(storeData!!, data.value!!)
                }
            }
        }
    }

    private fun calculatePlus(X: String, Y: String): String {
        return if (X.contains(".") || Y.contains(".")) {
            val x = X.toDouble()
            val y = Y.toDouble()
            (x + y).toString()
        } else {
            val x = X.toInt()
            val y = Y.toInt()
            (x + y).toString()
        }
    }

    private fun calculateMinus(X: String, Y: String): String {
        return if (X.contains(".") || Y.contains(".")) {
            val x = X.toDouble()
            val y = Y.toDouble()
            (x - y).toString()
        } else {
            val x = X.toInt()
            val y = Y.toInt()
            (x - y).toString()
        }
    }

    private fun calculateMultiple(X: String, Y: String): String {
        return if (X.contains(".") || Y.contains(".")) {
            val x = X.toDouble()
            val y = Y.toDouble()
            (x * y).toString()
        } else {
            val x = X.toInt()
            val y = Y.toInt()
            (x * y).toString()
        }
    }

    private fun calculateDivide(X: String, Y: String): String {
        return if (X.contains(".") || Y.contains(".")) {
            val x = X.toDouble()
            val y = Y.toDouble()
            (x / y).toString()
        } else {
            val x = X.toInt()
            val y = Y.toInt()
            (x / y).toString()
        }
    }
}