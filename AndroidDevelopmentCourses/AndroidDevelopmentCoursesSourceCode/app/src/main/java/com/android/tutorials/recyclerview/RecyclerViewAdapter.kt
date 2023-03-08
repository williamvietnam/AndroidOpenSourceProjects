package com.android.tutorials.recyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemRecyclerViewBinding

class RecyclerViewAdapter(
    private val itemList: List<RecyclerViewModel>?,
    private val callBack: ItemCallBack
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)
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
        private val binding: ItemRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val item = itemList?.get(position)
            if (item != null) {
                this.binding.tvUseCase.text = item.text
                this.binding.root.setOnClickListener {
                    callBack.onClicked(item = item)
                }
            }
        }
    }

    interface ItemCallBack {
        fun onClicked(item: RecyclerViewModel);
    }
}