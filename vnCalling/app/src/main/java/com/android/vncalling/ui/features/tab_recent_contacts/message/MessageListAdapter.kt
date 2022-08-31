package com.android.vncalling.ui.features.tab_recent_contacts.message

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vncalling.base.BaseViewHolder
import com.android.vncalling.data.remote.models.Message
import com.android.vncalling.databinding.ItemMessageBinding
import com.android.vncalling.utils.callback.MessageListCallBack
import com.squareup.picasso.Picasso

class MessageListAdapter(
    private val messageList: List<Message>?,
    private val callBack: MessageListCallBack
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMessageBinding = ItemMessageBinding.inflate(inflater, parent, false)
        return MessageListHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (messageList != null) {
            return messageList.size
        }
        return 0
    }

    inner class MessageListHolder(private val binding: ItemMessageBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            super.onBind(position)
            val item: Message? = messageList?.get(position)
            if (item != null) {
                Picasso.get().load(item.avatar).into(this.binding.avatar)
                this.binding.userName.text = item.userName
                this.binding.tvMessageContent.text = item.messageContent
            } else {
                Log.d(MessageListAdapter::class.java.simpleName, "Debug: MessageItem is Null")
            }
            this.binding.root.setOnClickListener {
                callBack.onItemClicked(item)
            }
        }

        override fun clear() {
        }
    }
}