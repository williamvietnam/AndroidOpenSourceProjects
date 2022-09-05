package com.android.vncalling.ui.features.tab_recent_contacts.message

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vncalling.base.BaseViewHolder
import com.android.vncalling.data.remote.models.Message
import com.android.vncalling.databinding.ItemLoadMoreBinding
import com.android.vncalling.databinding.ItemMessageBinding
import com.android.vncalling.ui.features.tab_contacts_list.ContactListAdapter
import com.android.vncalling.utils.callback.MessageListCallBack
import com.squareup.picasso.Picasso

class MessageListAdapter(
    private val messageList: MutableList<Message>?,
    private val callBack: MessageListCallBack
) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        const val VIEW_TYPE_MESSAGE_LIST = 0
        const val VIEW_TYPE_LOAD_MORE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOAD_MORE) {
            val loadMoreBinding = ItemLoadMoreBinding.inflate(inflater, parent, false)
            return LoadMoreViewHolder(loadMoreBinding)
        }
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

    override fun getItemViewType(position: Int): Int {
        if (messageList?.get(position) == Message("null", "null", "null", "null", "null")) {
            return VIEW_TYPE_LOAD_MORE
        }
        return VIEW_TYPE_MESSAGE_LIST
    }

    inner class MessageListHolder(
        private val binding: ItemMessageBinding
    ) : BaseViewHolder(binding.root) {

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

    inner class LoadMoreViewHolder(
        private val binding: ItemLoadMoreBinding
    ) : BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            super.onBind(position)
            Log.d(ContactListAdapter::class.java.simpleName, "debug: onBind()...in load more")
            this.binding.progressBar.visibility = View.VISIBLE
        }

        override fun clear() {
            Log.d(ContactListAdapter::class.java.simpleName, "debug: clear()...in load more")
        }
    }

    fun addLoadMore() {
        try {
            messageList?.add(Message("null", "null", "null", "null", "null"))
            notifyItemInserted(messageList?.size!! - 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeLoadMore() {
        try {
            if ((messageList?.size!! > 0) && (messageList[messageList.size - 1]
                        == Message("null", "null", "null", "null", "null"))
            ) {
                messageList.removeAt(messageList.size - 1)
                notifyItemRemoved(messageList.size)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}