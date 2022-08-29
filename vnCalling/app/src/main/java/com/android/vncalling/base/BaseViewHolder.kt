package com.android.vncalling.base

abstract class BaseViewHolder {

    fun onBind(position: Int) {
        clear()
    }

    abstract fun clear()
}