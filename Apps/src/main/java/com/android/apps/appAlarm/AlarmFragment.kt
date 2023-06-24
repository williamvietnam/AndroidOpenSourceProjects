package com.android.apps.appAlarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentAlarmBinding

class AlarmFragment : BaseFragment<FragmentAlarmBinding, AlarmViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAlarmBinding {
        return FragmentAlarmBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): AlarmViewModel {
        return ViewModelProvider(this)[AlarmViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {
    }
}