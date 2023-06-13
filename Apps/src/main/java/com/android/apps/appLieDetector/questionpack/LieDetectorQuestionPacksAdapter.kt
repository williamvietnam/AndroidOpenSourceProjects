package com.android.apps.appLieDetector.questionpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemLieDetectorQuestionPackBinding
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack

class LieDetectorQuestionPacksAdapter(
    private val questionPacks: MutableList<QuestionPack>,
    private val callback: QuestionPacksCallBack
) : RecyclerView.Adapter<LieDetectorQuestionPacksAdapter.LieDetectorQuestionsPackHolder>() {

    var selectedPosition = 0
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LieDetectorQuestionsPackHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLieDetectorQuestionPackBinding.inflate(inflater, parent, false)
        return LieDetectorQuestionsPackHolder(binding)
    }

    override fun onBindViewHolder(holder: LieDetectorQuestionsPackHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return questionPacks.size
    }

    inner class LieDetectorQuestionsPackHolder(
        private val binding: ItemLieDetectorQuestionPackBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val pack = questionPacks[position]
            binding.content.text = pack.questionPack
            binding.checkbox.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
                callback.onQuestionPackClick(pack)
            }
            binding.checkbox.isChecked = (selectedPosition == position)
        }
    }

    interface QuestionPacksCallBack {
        fun onQuestionPackClick(questionPack: QuestionPack)
    }
}