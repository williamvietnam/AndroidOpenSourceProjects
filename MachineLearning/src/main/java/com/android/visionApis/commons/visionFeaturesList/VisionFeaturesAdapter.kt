package com.android.visionApis.commons.visionFeaturesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemVisionFeatureBinding

class VisionFeaturesAdapter(
    private val data: MutableList<VisionFeatureModel>,
    private val callback: IVisionFeaturesCallBack
) : RecyclerView.Adapter<VisionFeaturesAdapter.VisionFeatureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisionFeatureViewHolder {
        val binding = ItemVisionFeatureBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VisionFeatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VisionFeatureViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class VisionFeatureViewHolder(
        private val binding: ItemVisionFeatureBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val feature = data[position]
            binding.title.text = feature.title
            binding.description.text = feature.description
            binding.root.background = AppCompatResources.getDrawable(
                itemView.context, feature.background
            )
            binding.root.setOnClickListener {
                callback.onFeatureClick(feature)
            }
        }
    }

    interface IVisionFeaturesCallBack {
        fun onFeatureClick(feature: VisionFeatureModel)
    }
}