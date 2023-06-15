package com.android.apps.appAIChat

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemChatMessageBinding
import com.bumptech.glide.Glide
import java.io.IOException

class AIChatAdapter(
    private val callback: AIChatCallBack
) : RecyclerView.Adapter<AIChatAdapter.AIChatViewHolder>() {

    private val messages: MutableList<Message> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AIChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChatMessageBinding.inflate(inflater, parent, false)
        return AIChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AIChatViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class AIChatViewHolder(
        private val binding: ItemChatMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val message = messages[position]
            if (message.sentBy == Message.SENT_BY_ME) {
                binding.llSentMessage.visibility = View.VISIBLE
                binding.rlReceivedMessage.visibility = View.GONE
                binding.tvSentMessage.text = message.message
                binding.buttonSendCopy.visibility = View.VISIBLE
                binding.buttonSendCopy.setOnClickListener {
                    callback.onCopyTextClicked(message.message!!)
                }
            } else {
                if (message.message == "Typing...") {
                    Glide.with(itemView.context)
                        .load(getImageFromAsset("app_ai_chat/aichatTyping.gif", itemView.context))
                        .into(binding.imvTyping)
                    binding.imvTyping.visibility = View.VISIBLE
                    binding.tvReceivedMessage.visibility = View.GONE
                    binding.buttonReceivedCopy.visibility = View.GONE
                } else {
                    binding.imvTyping.visibility = View.GONE
                    binding.tvReceivedMessage.visibility = View.VISIBLE
                    binding.buttonReceivedCopy.visibility = View.VISIBLE
                }
                binding.llSentMessage.visibility = View.GONE
                binding.rlReceivedMessage.visibility = View.VISIBLE

                if (message.message?.length!! < 400) {
                    binding.tvReceivedMessage.text = message.message
                    binding.tvUpGradle.visibility = View.GONE
                    binding.buttonUpGradle.visibility = View.GONE
                    callback.onTextToSpeechAIChat(message.message!!)
                    binding.buttonReceivedCopy.setOnClickListener {
                        callback.onCopyTextClicked(message.message!!)
                    }
                } else {
                    val messageNotFull = (message.message?.substring(0, 350) + "...")
                    binding.tvReceivedMessage.text = messageNotFull
                    binding.tvUpGradle.visibility = View.VISIBLE
                    binding.buttonUpGradle.visibility = View.VISIBLE
                    callback.onTextToSpeechAIChat(messageNotFull)
                    binding.buttonReceivedCopy.visibility = View.GONE
                    binding.buttonUpGradle.setOnClickListener {
                        callback.onTextToSpeechAIChat("")
                        binding.tvReceivedMessage.text = message.message
                        binding.tvUpGradle.visibility = View.GONE
                        binding.buttonUpGradle.visibility = View.GONE
                        callback.onTextToSpeechAIChat(message.message!!)
                        binding.buttonReceivedCopy.visibility = View.VISIBLE
                        binding.buttonReceivedCopy.setOnClickListener {
                            callback.onCopyTextClicked(message.message!!)
                        }
                    }
                }
            }
        }
    }

    fun loadMessage(message: Message) {
        messages.add(message)
        notifyItemChanged((messages.size - 1))
    }

    fun removeMessage() {
        messages.removeAt((messages.size - 1))
    }

    private fun getImageFromAsset(fileName: String, context: Context): Drawable? {
        var result: Drawable? = null
        try {
            val stream = context.assets.open(fileName)
            result = Drawable.createFromStream(stream, null)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return result
    }

    interface AIChatCallBack {
        fun onTextToSpeechAIChat(message: String)
        fun onCopyTextClicked(text: String)
    }
}