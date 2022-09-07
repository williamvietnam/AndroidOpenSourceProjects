package com.android.vncalling.ui.features.tab_personal

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vncalling.R
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.data.database.entities.Setting
import com.android.vncalling.databinding.FragmentPersonalBinding
import com.android.vncalling.ui.custom.toolbar.ToolbarCustom
import com.android.vncalling.utilities.Constants
import com.android.vncalling.utilities.callback.PersonalCallBack

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

        //--------------------------------- binds View ----------------------------------------
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        this.settingsAdapter = SettingsAdapter(viewModel.getSettingList(), this)
        this.binding.rcv.addItemDecoration(divider)
        this.binding.rcv.adapter = this.settingsAdapter

        //------------------------------ setup events click ---------------------------------------
        this.binding.toolbar.setToolbarRightCallBack(object : ToolbarCustom.ToolbarRightCallBack {
            override fun onRightClicked() {
                AlertDialog.Builder(requireContext())
                    .setTitle("Log out")
                    .setMessage("Do you want to logout account now?")
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
                showToast(PERSONAL_INFORMATION)
            }
            SECURITY_PRIVACY -> {
                showToast(SECURITY_PRIVACY)
            }
            LANGUAGE_SETTING -> {
                val languages = arrayOf(Constants.KEY_LANGUAGE_VI, Constants.KEY_LANGUAGE_EN)
                AlertDialog.Builder(requireContext())
                    .setTitle("Select language")
                    .setItems(languages, object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            TODO("Not yet implemented")
                        }
                    })
            }
        }
    }

    companion object {
        const val PERSONAL_INFORMATION = "PERSONAL_INFORMATION"

        const val SECURITY_PRIVACY = "SECURITY_PRIVACY"

        const val LANGUAGE_SETTING = "LANGUAGE_SETTING"
    }
}