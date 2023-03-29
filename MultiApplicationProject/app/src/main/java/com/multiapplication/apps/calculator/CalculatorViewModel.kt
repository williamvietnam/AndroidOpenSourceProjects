package com.multiapplication.apps.calculator

import com.multiapplication.base.BaseViewModel

class CalculatorViewModel : BaseViewModel() {

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