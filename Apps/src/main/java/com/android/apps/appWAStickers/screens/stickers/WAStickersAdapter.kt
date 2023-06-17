package com.android.apps.appWAStickers.screens.stickers

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.apps.appWAStickers.models.Sticker
import com.android.databinding.ItemStickerBinding
import com.bumptech.glide.Glide
import com.android.apps.appWAStickers.core.StickerPackLoader

class WAStickersAdapter(
    private val callback: IStickerCallBack
) : RecyclerView.Adapter<WAStickersAdapter.WAStickerHolder>() {
    private val stickers: ArrayList<Sticker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WAStickerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStickerBinding.inflate(inflater, parent, false)
        return WAStickerHolder(binding)
    }

    override fun onBindViewHolder(holder: WAStickerHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return stickers.size
    }

    inner class WAStickerHolder(
        private val binding: ItemStickerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val item = stickers[position]

            Glide.with(itemView.context).load(
                StickerPackLoader.getStickerAssetUri(item.identifier, item.imageFileName)
            ).into(binding.imvSticker)

            Log.d(
                "haha",
                "${StickerPackLoader.getStickerAssetUri(item.identifier, item.imageFileName)}"
            )
            this.binding.root.setOnClickListener {
                callback.onStickerClicked(stickers, position)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(data: ArrayList<Sticker>) {
        this.stickers.clear()
        this.stickers.addAll(data)
        notifyDataSetChanged()
    }

    interface IStickerCallBack {
        fun onStickerClicked(stickers: ArrayList<Sticker>, position: Int)
    }
}