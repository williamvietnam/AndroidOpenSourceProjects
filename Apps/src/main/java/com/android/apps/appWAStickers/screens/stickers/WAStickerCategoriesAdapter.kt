package com.android.apps.appWAStickers.screens.stickers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.android.R
import com.android.apps.appWAStickers.models.StickerPack
import com.android.databinding.ItemStickerCategoryBinding
import com.bumptech.glide.Glide
import java.io.IOException

class WAStickerCategoriesAdapter(
    private val callback: IStickerCategoryCallBack
) : RecyclerView.Adapter<WAStickerCategoriesAdapter.WAStickerCategoryHolder>() {

    private val stickerCategories: ArrayList<StickerPack> = ArrayList()
    var selectedPosition = 0
    private var lastSelectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WAStickerCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStickerCategoryBinding.inflate(inflater, parent, false)
        return WAStickerCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: WAStickerCategoryHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return stickerCategories.size
    }

    inner class WAStickerCategoryHolder(
        val binding: ItemStickerCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val item = stickerCategories[position]
            Glide.with(itemView.context).load(
                getImageFromAsset(
                    "${item.identifier}/${item.trayImageFile}",
                    itemView.context
                )
            ).into(binding.imageView)

            this.binding.root.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
                callback.onStickerCategoryClicked(item)
            }

            if (selectedPosition == position) {
                binding.root.background = AppCompatResources.getDrawable(
                    itemView.context, R.drawable.background_rectangle_cac5e4
                )
            } else {
                binding.root.background = AppCompatResources.getDrawable(
                    itemView.context, android.R.color.transparent
                )
            }
        }
    }

    fun getImageFromAsset(fileName: String, context: Context): Drawable? {
        var result: Drawable? = null
        try {
            val stream = context.assets.open(fileName)
            result = Drawable.createFromStream(stream, null)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return result
    }

    @SuppressLint("notifyDataSetChanged")
    fun loadData(data: ArrayList<StickerPack>) {
        this.stickerCategories.clear()
        this.stickerCategories.addAll(data)
        notifyDataSetChanged()
    }

    fun removeItemsSelected() {
        selectedPosition = -1
        lastSelectedPosition = -1
        notifyDataSetChanged()
    }

    interface IStickerCategoryCallBack {
        fun onStickerCategoryClicked(item: StickerPack)
    }
}