package com.android.apps.appPrankSound.sound

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.R
import com.android.apps.appPrankSound.data.models.Sound
import com.android.databinding.ItemSoundBinding
import com.bumptech.glide.Glide
import java.io.IOException

class FavouriteSoundsAdapter(
    private val callback: IFavouriteSoundsCallBack
) : RecyclerView.Adapter<FavouriteSoundsAdapter.FavouriteSoundsViewHolder>() {

    private val sounds: MutableList<Sound> = ArrayList()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteSoundsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSoundBinding.inflate(inflater, parent, false)
        return FavouriteSoundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteSoundsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return sounds.size
    }

    inner class FavouriteSoundsViewHolder(
        private val binding: ItemSoundBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val sound = sounds[position]

            //set background color item
            this.binding.cardView.setCardBackgroundColor(
                itemView.context.resources.getColor(
                    arrayColors[position % 12]
                )
            )

            //set icon card
            val icon = getImageFromAsset(
                "app_prank_sounds/images/${sound.category}/${sound.icon}.png",
                itemView.context
            )
            Glide.with(itemView.context).load(icon).into(binding.image)

            //set name card
            binding.text.text = sound.name

            binding.root.setOnClickListener {
                callback.onSoundClick(sound)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(sounds: MutableList<Sound>) {
        sounds.clear()
        sounds.addAll(sounds)
        notifyDataSetChanged()
    }

    private fun getImageFromAsset(fileName: String, context: Context): Drawable? {
        val result: Drawable?
        try {
            val stream = context.assets.open(fileName)
            result = Drawable.createFromStream(stream, null)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return result
    }

    interface IFavouriteSoundsCallBack {
        fun onSoundClick(sound: Sound)
    }
}