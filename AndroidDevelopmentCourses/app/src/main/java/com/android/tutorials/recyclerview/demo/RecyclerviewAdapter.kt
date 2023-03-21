package com.android.tutorials.recyclerview.demo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemRecyclerViewBinding

class RecyclerviewAdapter(
    private val itemList: List<RecyclerviewModel>?,
    private val callBack: ItemCallBack
) : RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)
        return RecyclerviewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerviewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (itemList != null) {
            return itemList.size;
        }
        return 0
    }

    inner class RecyclerviewHolder(
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
        fun onClicked(item: RecyclerviewModel);
    }
}