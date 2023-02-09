package com.android.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.LayoutItemUseCaseBinding

class LayoutItemUseCaseAdapter(
    private val useCaseList: List<String>?,
    private val callBack: UseCaseCallBack
) : RecyclerView.Adapter<LayoutItemUseCaseAdapter.LayoutItemUseCaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutItemUseCaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemUseCaseBinding.inflate(inflater, parent, false)
        return LayoutItemUseCaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LayoutItemUseCaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (useCaseList != null) {
            return useCaseList.size
        }
        return 0
    }

    inner class LayoutItemUseCaseViewHolder(
        private val binding: LayoutItemUseCaseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            //todo
        }
    }

    interface UseCaseCallBack {
        fun onClicked();
    }
}