package com.android.remotes.lg.widget

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.R
import com.connectsdk.core.AppInfo

class AppAdapter(
    private var context: Context, private var resourceId: Int
) : ArrayAdapter<AppInfo>(context, resourceId) {

    private var runningAppId: String? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (convertView == null) {
            view = View.inflate(context, resourceId, null)
        }

        val app = getItem(position)

        val textView = view?.findViewById<View>(R.id.itemTitle) as TextView
        val subTextView = view.findViewById<View>(R.id.itemSubTitle) as TextView
        val imageView = view.findViewById<View>(R.id.itemCheck) as ImageView
        textView.text = app!!.name
        subTextView.text = app.id
        if (runningAppId != null && runningAppId == app.id) {
            println("runningAppId: " + runningAppId + ", id: " + app.id)
            textView.setTextColor(Color.RED)
            imageView.visibility = View.VISIBLE
        } else {
            textView.setTextColor(Color.BLACK)
            imageView.visibility = View.INVISIBLE
        }

        return view
    }

    fun setRunningAppId(appId: String) {
        runningAppId = appId
    }

    fun sort() {
        this.sort(object : Comparator<AppInfo?> {
            override fun compare(o1: AppInfo?, o2: AppInfo?): Int {
                return o1?.name!!.compareTo(o2?.name!!)
            }
        })
    }
}