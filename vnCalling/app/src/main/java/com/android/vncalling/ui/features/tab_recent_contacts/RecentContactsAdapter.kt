package com.android.vncalling.ui.features.tab_recent_contacts

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.vncalling.ui.features.tab_recent_contacts.call.CallListFragment
import com.android.vncalling.ui.features.tab_recent_contacts.message.MessageListFragment

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