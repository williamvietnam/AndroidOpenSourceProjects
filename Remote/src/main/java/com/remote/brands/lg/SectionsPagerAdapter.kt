package com.android.remotes.lg

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.remote.R
import com.remote.brands.lg.features.AppsFragment
import com.remote.brands.lg.features.KeyControlFragment
import com.remote.brands.lg.features.MediaPlayerFragment
import com.remote.brands.lg.features.SystemFragment
import com.remote.brands.lg.features.TVFragment
import com.remote.brands.lg.features.WebAppFragment
import com.remote.brands.lg.LGBaseFragment

class SectionsPagerAdapter(
    private var context: Context,
    private var fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private var mItems: IntArray
    private var mTitles: Array<String>

    init {
        val items = context.resources.obtainTypedArray(R.array.tab_icons)
        mTitles = context.resources.getStringArray(R.array.tab_titles)
        mItems = IntArray(items.length())
        for (i in 0 until items.length()) {
            mItems[i] = items.getResourceId(i, -1)
        }
        items.recycle()
    }

    override fun getItem(position: Int): Fragment {
        val newFragment: LGBaseFragment

        when (position) {
            1 -> newFragment = WebAppFragment(context)
            2 -> newFragment = KeyControlFragment(context)
            3 -> newFragment = AppsFragment(context)
            4 -> newFragment = TVFragment(context)
            5 -> newFragment = SystemFragment(context)
            0 -> newFragment = MediaPlayerFragment(context)
            else -> newFragment = MediaPlayerFragment(context)
        }

        return newFragment
    }

    fun getFragment(position: Int): LGBaseFragment? {
        return fragmentManager.findFragmentByTag("android:switcher:" + R.id.mViewPager + ":" + position) as LGBaseFragment?
    }

    override fun getCount(): Int {
        return mItems.size
    }

    fun getIcon(position: Int): Int {
        return mItems[position]
    }

    fun getTitle(position: Int): String? {
        return mTitles[position]
    }
}