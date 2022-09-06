package com.android.vncalling.ui.features.tab_recent_contacts.recent_call_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vncalling.base.BaseViewHolder
import com.android.vncalling.data.remote.models.UserInformation
import com.android.vncalling.databinding.ItemContactListBinding
import com.android.vncalling.databinding.ItemLoadMoreBinding
import com.android.vncalling.ui.features.tab_contacts_list.ContactListAdapter
import com.android.vncalling.utilities.callback.ContactListCallBack
import com.squareup.picasso.Picasso

class CallListAdapter(
    private val userInformationList: MutableList<UserInformation>?,
    private val callBack: ContactListCallBack
) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        const val VIEW_TYPE_CALL_LIST = 0
        const val VIEW_TYPE_LOAD_MORE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOAD_MORE) {
            val loadMoreBinding = ItemLoadMoreBinding.inflate(inflater, parent, false)
            return LoadMoreViewHolder(loadMoreBinding)
        }
        val binding = ItemContactListBinding.inflate(inflater, parent, false)
        return CallListHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (userInformationList != null) {
            return this.userInformationList.size
        }
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        if (userInformationList?.get(position) == UserInformation("null", "null", "null", "null")) {
            return VIEW_TYPE_LOAD_MORE
        }
        return VIEW_TYPE_CALL_LIST
    }

    inner class CallListHolder(private val binding: ItemContactListBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            super.onBind(position)
            val item: UserInformation? = userInformationList?.get(position)
            Picasso.get()
                .load(item?.avatar)
                .centerCrop()
                .into(this.binding.avatar)
            this.binding.userName.text = item?.userName
            this.binding.accountName.text = item?.accountName
            this.binding.btnMessage.setOnClickListener {
                callBack.onMessageClicked(item)
            }
            this.binding.btnAudioCall.setOnClickListener {
                callBack.onAudioCallClicked(item)
            }
            this.binding.btnVideoCall.setOnClickListener {
                callBack.onVideoCallClicked(item)
            }
        }

        override fun clear() {
            this.binding.avatar.setImageDrawable(null)
            this.binding.userName.text = ""
            this.binding.accountName.text = ""
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
            userInformationList?.add(UserInformation("null", "null", "null", "null"))
            notifyItemInserted(userInformationList?.size!! - 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeLoadMore() {
        try {
            if ((userInformationList?.size!! > 0) && (userInformationList[userInformationList.size - 1]
                        == UserInformation("null", "null", "null", "null"))
            ) {
                userInformationList.removeAt(userInformationList.size - 1)
                notifyItemRemoved(userInformationList.size)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}