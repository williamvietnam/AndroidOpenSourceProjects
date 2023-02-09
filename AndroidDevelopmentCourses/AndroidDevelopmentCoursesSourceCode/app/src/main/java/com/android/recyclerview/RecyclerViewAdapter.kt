package com.android.recyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.LayoutItemRecyclerViewBinding

class RecyclerViewAdapter(
    private val itemList: List<String>?,
    private val callBack: ItemCallBack
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemRecyclerViewBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (itemList != null) {
            return itemList.size;
        }
        return 0
    }

    inner class RecyclerViewHolder(
        private val binding: LayoutItemRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            //todo
        }
    }

    interface ItemCallBack {
        fun onClicked();
    }
}