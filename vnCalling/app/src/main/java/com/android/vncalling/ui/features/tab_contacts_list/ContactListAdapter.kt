package com.android.vncalling.ui.features.tab_contacts_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vncalling.base.BaseViewHolder
import com.android.vncalling.data.remote.models.UserInformation
import com.android.vncalling.databinding.ItemContactListBinding
import com.android.vncalling.utils.callback.ContactListCallBack
import com.squareup.picasso.Picasso

class ContactListAdapter(
    private val userInformationList: List<UserInformation>?,
    private val callBack: ContactListCallBack
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactListBinding.inflate(inflater, parent, false)
        return ContactListHolder(binding)
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

    inner class ContactListHolder(private val binding: ItemContactListBinding) :
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
}