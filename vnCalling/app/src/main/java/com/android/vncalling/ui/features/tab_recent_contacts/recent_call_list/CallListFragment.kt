package com.android.vncalling.ui.features.tab_recent_contacts.recent_call_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.data.remote.models.UserInformation
import com.android.vncalling.databinding.FragmentCallListBinding
import com.android.vncalling.utils.callback.ContactListCallBack

class CallListFragment : BaseFragment<FragmentCallListBinding, CallListViewModel>(),
    ContactListCallBack {

    private lateinit var adapter: CallListAdapter

    override fun createViewModel(): CallListViewModel =
        ViewModelProvider(this)[CallListViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCallListBinding {
        return FragmentCallListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        hideBottomNavigationView(false)
        this.adapter = CallListAdapter(viewModel.getUserInformationListFailure(), this)
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        this.binding.root.addItemDecoration(divider)
        this.binding.root.adapter = this.adapter
    }

    override fun onMessageClicked(item: UserInformation?) {
    }

    override fun onAudioCallClicked(item: UserInformation?) {
    }

    override fun onVideoCallClicked(item: UserInformation?) {
    }
}