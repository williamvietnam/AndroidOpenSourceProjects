package com.android.vncalling.ui.features.tab_personal

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vncalling.R
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.data.database.entities.Setting
import com.android.vncalling.databinding.FragmentPersonalBinding
import com.android.vncalling.ui.custom.toolbar.ToolbarCustom
import com.android.vncalling.utils.callback.PersonalCallBack

class PersonalFragment : BaseFragment<FragmentPersonalBinding, PersonalViewModel>(),
    PersonalCallBack {

    private lateinit var settingsAdapter: SettingsAdapter

    override fun createViewModel(): PersonalViewModel =
        ViewModelProvider(this)[PersonalViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        hideBottomNavigationView(false)
        //--------------------------------- binds View ----------------------------------------
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        this.settingsAdapter = SettingsAdapter(viewModel.getSettingList(), this)
        this.binding.rcv.addItemDecoration(divider)
        this.binding.rcv.adapter = this.settingsAdapter

        //------------------------------ setup events click ---------------------------------------
        this.binding.toolbar.setToolbarRightCallBack(object : ToolbarCustom.ToolbarRightCallBack {
            override fun onRightClicked() {
                AlertDialog.Builder(context)
                    .setTitle("Log out")
                    .setMessage("Do you want logout account now?")
                    .setIcon(R.drawable.ic_logout)
                    .setCancelable(false)
                    .setPositiveButton("Accept", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, id: Int) {
                            if (viewModel.isLogoutSuccess()) {
                                getMainInstance().openLoginActivity()
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        })
    }

    override fun onItemClicked(item: Setting?) {
        when (item?.settingId) {
            PERSONAL_INFORMATION -> {
                //TODO("update personal information!")
            }
        }
    }

    companion object {
        const val PERSONAL_INFORMATION = "PERSONAL_INFORMATION"
    }
}