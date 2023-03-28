package com.multiapplication.container

import com.multiapplication.R
import com.multiapplication.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    fun createApps(): List<App> {
        val apps = ArrayList<App>()

        val calculator = App(
            id = Constants.CALCULATOR,
            logo = R.drawable.ic_calculator,
            name = "Calculator"
        )
        apps.add(calculator)

        val media = App(
            id = Constants.MEDIA,
            logo = R.drawable.ic_song,
            name = "Media"
        )
        apps.add(media)

        val calendar = App(
            id = Constants.CALENDAR,
            logo = R.drawable.ic_calendar,
            name = "Calendar"
        )
        apps.add(calendar)

        val notes = App(
            id = Constants.NOTES,
            logo = R.drawable.ic_notes,
            name = "Notes"
        )
        apps.add(notes)

        return apps
    }
}