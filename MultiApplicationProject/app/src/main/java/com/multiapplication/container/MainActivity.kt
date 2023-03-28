package com.multiapplication.container

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.multiapplication.apps.calculator.CalculatorActivity
import com.multiapplication.apps.media.ui.main.MediaActivity
import com.multiapplication.base.BaseActivity
import com.multiapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initializeView() {
        val adapter = AppsAdapter(
            apps = viewModel.createApps(),
            callback = object : AppsAdapter.CallBack {
                override fun onClicked(app: App) {
                    when (app.id) {
                        Constants.CALCULATOR -> {
                            startActivity(
                                Intent(
                                    applicationContext,
                                    CalculatorActivity::class.java
                                )
                            )
                        }

                        Constants.MEDIA -> {
                            startActivity(
                                Intent(
                                    applicationContext,
                                    MediaActivity::class.java
                                )
                            )
                        }

                        Constants.CALENDAR -> {

                        }

                        Constants.NOTES -> {

                        }

                    }
                }
            }
        )

        this.binding.recyclerview.adapter = adapter
    }
}