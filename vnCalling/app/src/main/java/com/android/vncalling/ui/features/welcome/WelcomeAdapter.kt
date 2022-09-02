package com.android.vncalling.ui.features.welcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vncalling.base.BaseViewHolder
import com.android.vncalling.data.database.entities.Welcome
import com.android.vncalling.databinding.ItemWelcomeBinding

class WelcomeAdapter(
    private val welcomeList: List<Welcome>?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemWelcomeBinding = ItemWelcomeBinding.inflate(inflater, parent, false)
        return WelcomeHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position = position)
    }

    override fun getItemCount(): Int {
        if (welcomeList != null) {
            return welcomeList.size
        }
        return 0
    }

    inner class WelcomeHolder(
        private val binding: ItemWelcomeBinding
    ) : BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            super.onBind(position)
            val item: Welcome? = welcomeList?.get(position)
            if (item != null) {
                this.binding.thumbnail.setImageResource(item.thumbnail)
                this.binding.tvHeadline.setText(item.headline)
                this.binding.tvDescription.setText(item.description)
            }
        }

        override fun clear() {
            this.binding.thumbnail.setImageDrawable(null)
            this.binding.tvHeadline.text = ""
            this.binding.tvDescription.text = ""
        }
    }
}