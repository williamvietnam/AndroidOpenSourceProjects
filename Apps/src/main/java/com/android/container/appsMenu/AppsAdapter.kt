package com.android.container.appsMenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.databinding.ItemAppBinding

class AppsAdapter(
    private val apps: List<App>?,
    private val callback: CallBack,
) : Adapter<AppsAdapter.AppsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAppBinding.inflate(inflater, parent, false)
        return AppsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppsViewHolder, position: Int) {
        holder.onBind(position = position)
    }

    override fun getItemCount(): Int {
        if (apps != null) {
            return apps.size
        }
        return 0
    }

    inner class AppsViewHolder(
        private val binding: ItemAppBinding
    ) : ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val app = apps?.get(position)
            if (app != null) {
                this.binding.image.setImageResource(app.logo)
                this.binding.text.text = app.name

                this.binding.root.setOnClickListener {
                    callback.onClicked(app)
                }
            }
        }
    }

    interface CallBack {
        fun onClicked(app: App)
    }
}