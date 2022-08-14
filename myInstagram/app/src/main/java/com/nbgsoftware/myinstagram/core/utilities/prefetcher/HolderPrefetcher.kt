package com.nbgsoftware.myinstagram.core.utilities.prefetcher

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: William Giang Nguyen | 8/7/2022
 * */
typealias HolderCreator = (fakeParent: ViewGroup, viewType: Int) -> RecyclerView.ViewHolder

interface HolderPrefetch {
    fun setViewsCount(
        viewType: Int,
        count: Int,
        holderCreator: HolderCreator
    )
}

