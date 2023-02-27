package com.android.container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemTutorialBinding

class TutorialsAdapter(
    private val useCaseList: List<UseCase>?,
    private val callBack: UseCaseCallBack
) : RecyclerView.Adapter<TutorialsAdapter.TutorialsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTutorialBinding.inflate(inflater, parent, false)
        return TutorialsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TutorialsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (useCaseList != null) {
            return useCaseList.size
        }
        return 0
    }

    inner class TutorialsViewHolder(
        private val binding: ItemTutorialBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val useCase = useCaseList?.get(position)
            if (useCase != null) {
                this.binding.imageUseCase.setImageResource(useCase.image)
                this.binding.textUseCase.text = useCase.text
                this.binding.root.setOnClickListener {
                    callBack.useCaseOnClicked(useCase)
                }
            }
        }
    }
}