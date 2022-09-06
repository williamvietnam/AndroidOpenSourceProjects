package com.android.vncalling.ui.features.tab_personal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vncalling.base.BaseViewHolder
import com.android.vncalling.data.database.entities.Setting
import com.android.vncalling.databinding.ItemSettingBinding
import com.android.vncalling.utilities.callback.PersonalCallBack

class SettingsAdapter(
    private val settingList: List<Setting>,
    private val callBack: PersonalCallBack
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingBinding.inflate(inflater, parent, false)
        return SettingsHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return settingList.size
    }

    inner class SettingsHolder(
        private val binding: ItemSettingBinding
    ) : BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            super.onBind(position)
            val item = settingList[position]
            this.binding.logo.setImageResource(item.logo)
            this.binding.tvHeadline.text = item.headline
            this.binding.tvDescription.text = item.description
            this.binding.root.setOnClickListener {
                callBack.onItemClicked(item)
            }
        }

        override fun clear() {
        }
    }
}