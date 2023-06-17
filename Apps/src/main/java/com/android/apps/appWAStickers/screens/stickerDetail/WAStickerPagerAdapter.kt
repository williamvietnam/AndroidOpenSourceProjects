package com.android.apps.appWAStickers.screens.stickerDetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.apps.appWAStickers.core.StickerPackLoader
import com.android.apps.appWAStickers.models.Sticker
import com.android.databinding.ItemStickerPagerBinding
import com.bumptech.glide.Glide

class WAStickerPagerAdapter :
    RecyclerView.Adapter<WAStickerPagerAdapter.StickerDetailPagerHolder>() {

    private val stickers: ArrayList<Sticker> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerDetailPagerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStickerPagerBinding.inflate(inflater, parent, false)
        return StickerDetailPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: StickerDetailPagerHolder, position: Int) {
        holder.setData(stickers[position])
    }

    override fun getItemCount(): Int {
        return stickers.size
    }

    inner class StickerDetailPagerHolder(
        private val binding: ItemStickerPagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setData(sticker: Sticker) {
            Glide.with(itemView.context).load(
                StickerPackLoader.getStickerAssetUri(
                    sticker.identifier,
                    sticker.imageFileName
                )
            ).into(binding.image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(data: ArrayList<Sticker>) {
        stickers.clear()
        stickers.addAll(data)
        notifyDataSetChanged()
    }
}