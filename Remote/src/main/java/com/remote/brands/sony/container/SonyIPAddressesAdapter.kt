package com.remote.brands.sony.container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remote.databinding.ItemIpAddressBinding

class SonyIPAddressesAdapter(
    private val callback: ISonyIPAddressesCallBack,
    private val ipAddressesList: MutableList<String>
) : RecyclerView.Adapter<SonyIPAddressesAdapter.SonyIPAddressesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SonyIPAddressesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIpAddressBinding.inflate(inflater, parent, false)
        return SonyIPAddressesHolder(binding)
    }

    override fun onBindViewHolder(holder: SonyIPAddressesHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return ipAddressesList.size
    }

    inner class SonyIPAddressesHolder(
        private val binding: ItemIpAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = ipAddressesList[position]
            binding.root.text = item
            binding.root.setOnClickListener {
                callback.onSonyIPAddressClick()
            }
        }
    }

    interface ISonyIPAddressesCallBack {
        fun onSonyIPAddressClick()
    }
}