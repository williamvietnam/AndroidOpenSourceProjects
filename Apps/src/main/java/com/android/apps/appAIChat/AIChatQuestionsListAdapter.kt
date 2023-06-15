package com.android.apps.appAIChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemAIQuestionBinding

class AIChatQuestionsListAdapter(
    private val messages: MutableList<Message>?, private val callback: AIChatQuestionCallBack
) : RecyclerView.Adapter<AIChatQuestionsListAdapter.AIChatQuestionsListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AIChatQuestionsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAIQuestionBinding.inflate(inflater, parent, false)
        return AIChatQuestionsListViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: AIChatQuestionsListViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (messages != null) {
            return messages.size
        }
        return 0
    }

    inner class AIChatQuestionsListViewHolder(
        private val binding: ItemAIQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val item = messages?.get(position)
            if (item != null) {
                binding.text.text = item.message
                binding.root.setOnClickListener {
                    callback.onAIChatQuestionClicked(item)
                }
            }
        }
    }

    interface AIChatQuestionCallBack {
        fun onAIChatQuestionClicked(message: Message)
    }
}