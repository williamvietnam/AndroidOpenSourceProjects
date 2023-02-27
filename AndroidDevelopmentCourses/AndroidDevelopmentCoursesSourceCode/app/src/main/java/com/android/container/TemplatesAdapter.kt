package com.android.container

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemTemplateBinding

class TemplatesAdapter(
    private val useCaseList: List<UseCase>?,
    private val callBack: UseCaseCallBack
) : RecyclerView.Adapter<TemplatesAdapter.TemplatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplatesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTemplateBinding.inflate(inflater, parent, false)
        return TemplatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TemplatesViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (useCaseList != null) {
            return useCaseList.size
        }
        return 0
    }

    inner class TemplatesViewHolder(
        private val binding: ItemTemplateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val useCase = useCaseList?.get(position)
            if (useCase != null) {
                this.binding.imageUseCase.setImageResource(useCase.image)
                this.binding.textUseCase.text = useCase.text
                this.binding.root.setOnClickListener {
                    Log.d(TemplatesAdapter::class.java.name, "onClicked()...")
                    callBack.useCaseOnClicked(useCase)
                }
            }
        }
    }
}