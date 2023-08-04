package com.data.storages.commons.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.data.storages.databinding.ItemHomeBinding

class HomeAdapter(
    private val items: MutableList<HomeItem>,
    private val callback: IHomeCallBack
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class HomeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            binding.functionIcon.setImageResource(items[position].functionIcon)
            binding.functionName.text = items[position].functionName
            binding.root.background.setTint(
                itemView.context.resources.getColor(items[position].backgroundColor, null)
            )

            binding.root.setOnClickListener {
                Log.d(TAG, "onBind() -> clicked: ${items[position].id}")
                callback.onItemClick(items[position])
            }
        }
    }

    interface IHomeCallBack {
        fun onItemClick(item: HomeItem)
    }

    companion object {
        const val TAG = "HomeAdapter"
    }
}