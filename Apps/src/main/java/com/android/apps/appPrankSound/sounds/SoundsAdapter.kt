package com.android.apps.appPrankSound.sounds

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.R
import com.android.apps.appPrankSound.data.models.Sound
import com.android.databinding.ItemSoundBinding
import com.bumptech.glide.Glide
import java.io.IOException

class SoundsAdapter(
    private val sounds: MutableList<Sound>,
    private val callback: ISoundsCallBack
) : RecyclerView.Adapter<SoundsAdapter.SoundsViewHolder>() {

    private var arrayColors: Array<Int> = arrayOf(
        R.color.all_view_background_1,
        R.color.all_view_background_2,
        R.color.all_view_background_3,
        R.color.all_view_background_4,
        R.color.all_view_background_5,
        R.color.all_view_background_6,
        R.color.all_view_background_7,
        R.color.all_view_background_8,
        R.color.all_view_background_9,
        R.color.all_view_background_10,
        R.color.all_view_background_11,
        R.color.all_view_background_12
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSoundBinding.inflate(inflater, parent, false)
        return SoundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoundsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return sounds.size
    }

    inner class SoundsViewHolder(
        private val binding: ItemSoundBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = sounds[position] as Sound
            //set background color item
            this.binding.cardView.setCardBackgroundColor(
                itemView.context.resources.getColor(
                    arrayColors[position % 12]
                )
            )

            //set icon card
            if (item.icon != null) {
                this.binding.image.visibility = View.VISIBLE
                val icon = getImageFromAsset(
                    "app_prank_sounds/images/${item.category}/${item.icon}.png",
                    itemView.context
                )
                Glide.with(itemView.context).load(icon).into(binding.image)
            } else {
                this.binding.image.visibility = View.INVISIBLE
            }

            //set name card
            if (item.name != null) {
                this.binding.text.text = item.name
            }

            this.binding.root.setOnClickListener {
                callback.onSoundClick(item)
            }
        }
    }

    private fun getImageFromAsset(fileName: String, context: Context): Drawable? {
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

    interface ISoundsCallBack {
        fun onSoundClick(sound: Sound)
    }
}