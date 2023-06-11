package com.android.apps.appPrankSound.soundCategories

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.R
import com.android.apps.appPrankSound.data.models.SoundCategory
import com.android.databinding.ItemSoundCategoryBinding
import java.io.IOException

class SoundCategoriesAdapter(
    private val soundCategories: MutableList<SoundCategory>,
    private val callback: ISoundCategoriesCallBack
) : RecyclerView.Adapter<SoundCategoriesAdapter.SoundCategoriesViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundCategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSoundCategoryBinding.inflate(inflater, parent, false)
        return SoundCategoriesViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: SoundCategoriesViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return soundCategories.size
    }

    inner class SoundCategoriesViewHolder(
        private val binding: ItemSoundCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = soundCategories[position]

            //set background color item
            this.binding.cardView.setCardBackgroundColor(
                itemView.context.resources.getColor(
                    arrayColors[position % 12]
                )
            )

            val icon = getImageFromAsset(
                "app_prank_sounds/images/${item.id}/${item.iconCategory}.png",
                itemView.context
            )
            binding.image.setImageDrawable(icon)


            //set name card
            if (item.nameCategory != null) {
                this.binding.text.text = item.nameCategory
            }

            this.binding.root.setOnClickListener {
                callback.onSoundCategoryClick(item)
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

    interface ISoundCategoriesCallBack {
        fun onSoundCategoryClick(soundCategory: SoundCategory)
    }
}