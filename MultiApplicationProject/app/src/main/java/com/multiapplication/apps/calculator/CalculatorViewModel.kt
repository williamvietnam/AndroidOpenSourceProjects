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
                storeData = data.value!!
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
            if (isEnablePlusButton) {
                this.calculatePlus(storeData!!, data.value!!)
            } else if (isEnableMinusButton) {
                this.calculateMinus(storeData!!, data.value!!)
            } else if (isEnableMultipleButton) {
                this.calculateMultiple(storeData!!, data.value!!)
            } else if (isEnableDivideButton) {
                this.calculateDivide(storeData!!, data.value!!)
            }
        }
    }

    fun getCalculationResultWhenClickEqualButton() {
        if (storeData != null && data.value != null) {
            when (typeButton) {
                CalculatorConstants.PLUS -> {
                    this.calculatePlus(storeData!!, data.value!!)
                }
                CalculatorConstants.MINUS -> {
                    this.calculateMinus(storeData!!, data.value!!)
                }
                CalculatorConstants.MULTIPLE -> {
                    this.calculateMultiple(storeData!!, data.value!!)
                }
                CalculatorConstants.DIVIDE -> {
                    this.calculateDivide(storeData!!, data.value!!)
                }
            }
        }
    }

    private fun calculatePlus(X: String, Y: String): String {
        val x: Double = X.toDouble()
        val y: Double = Y.toDouble()
        return (x + y).toString()
    }

    private fun calculateMinus(X: String, Y: String): String {
        val x: Double = X.toDouble()
        val y: Double = Y.toDouble()
        return (x - y).toString()
    }

    private fun calculateMultiple(X: String, Y: String): String {
        val x: Double = X.toDouble()
        val y: Double = Y.toDouble()
        return (x * y).toString()
    }

    private fun calculateDivide(X: String, Y: String): String {
        val x: Double = X.toDouble()
        val y: Double = Y.toDouble()
        return (x / y).toString()
    }
}