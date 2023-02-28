package com.android.container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemLibraryBinding

class LibrariesAdapter(
    private val useCases: List<UseCase>?,
    private val callBack: UseCaseCallBack
) : RecyclerView.Adapter<LibrariesAdapter.LibrariesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrariesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLibraryBinding.inflate(inflater, parent, false)
        return LibrariesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibrariesViewHolder, position: Int) {
        holder.onBind(position = position)
    }

    override fun getItemCount(): Int {
        if (useCases != null) {
            return useCases.size
        }
        return 0
    }

    inner class LibrariesViewHolder(
        private val binding: ItemLibraryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val useCase = useCases?.get(position)
            if (useCase != null) {
                this.binding.imageUseCase.setImageResource(useCase.image)
                this.binding.textUseCase.text = useCase.text
                this.binding.root.setOnClickListener {
                    callBack.useCaseOnClicked(useCase = useCase)
                }
            }
        }
    }
}