package com.android.vncalling.ui.features.tab_recent_contacts.recent_message_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.data.remote.models.Message
import com.android.vncalling.databinding.FragmentMessageListBinding
import com.android.vncalling.utilities.callback.MessageListCallBack

class MessageListFragment : BaseFragment<FragmentMessageListBinding, MessageListViewModel>(),
    MessageListCallBack {

    private lateinit var adapter: MessageListAdapter

    override fun createViewModel(): MessageListViewModel =
        ViewModelProvider(this)[MessageListViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMessageListBinding {
        return FragmentMessageListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {

        this.adapter = MessageListAdapter(viewModel.getMessageList(), this)
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        this.binding.root.addItemDecoration(divider)
        this.binding.root.adapter = this.adapter
    }

    override fun onItemClicked(item: Message?) {
    }
}