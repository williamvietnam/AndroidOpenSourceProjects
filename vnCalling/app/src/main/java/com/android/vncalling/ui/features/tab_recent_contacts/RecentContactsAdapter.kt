package com.android.vncalling.ui.features.tab_recent_contacts

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.vncalling.ui.features.tab_recent_contacts.recent_call_list.CallListFragment
import com.android.vncalling.ui.features.tab_recent_contacts.recent_message_list.MessageListFragment

class RecentContactsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return MessageListFragment()
        } else if (position == 1) {
            return CallListFragment()
        }
        return MessageListFragment()
    }
}