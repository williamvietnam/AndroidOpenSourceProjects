package com.remote.brands.lg.widget

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.connectsdk.core.ChannelInfo
import com.remote.R

class ChannelAdapter : ArrayAdapter<ChannelInfo> {

    private var resourceId = 0
    private var currentChannel: ChannelInfo? = null

    constructor(context: Context, resource: Int) : super(context, resource) {
        this.resourceId = resource
    }

    constructor(context: Context, resource: Int, textViewResourceId: Int)
            : super(context, resource, textViewResourceId) {
        this.resourceId = resource
    }

    constructor(context: Context, resource: Int, channels: Array<ChannelInfo>) :
            super(context, resource, channels) {
        this.resourceId = resource
    }

    constructor(context: Context, resource: Int, objects: MutableList<ChannelInfo>) :
            super(context, resource, objects) {
        this.resourceId = resource
    }

    constructor(
        context: Context, resource: Int,
        textViewResourceId: Int, channels: Array<ChannelInfo>
    ) : super(context, resource, textViewResourceId, channels) {
        this.resourceId = resource
    }

    constructor(
        context: Context,
        resource: Int,
        textViewResourceId: Int,
        objects: MutableList<ChannelInfo>
    ) : super(context, resource, textViewResourceId, objects) {
        this.resourceId = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (convertView == null) {
            view = View.inflate(context, resourceId, null)
        }
        val app = getItem(position)
        val textView = view!!.findViewById<View>(R.id.itemTitle) as TextView
        val subTextView = view.findViewById<View>(R.id.itemSubTitle) as TextView
        val channelNumber = view.findViewById<View>(R.id.itemNumber) as TextView
        textView.text = app!!.name
        subTextView.text = app.id
        channelNumber.text = app.number
        val isChannel = app == currentChannel
        val textColor = if (isChannel) -0x1 else -0x1000000
        view.setBackgroundColor(if (isChannel) -0xab0000 else 0)
        textView.setTextColor(textColor)
        subTextView.setTextColor(textColor)
        channelNumber.setTextColor(textColor)
        return view
    }

    fun setCurrentChannel(currentChannel: ChannelInfo?) {
        this.currentChannel = currentChannel
    }

    fun getCurrentPosition(): Int {
        if (currentChannel == null) return -1
        for (i in 0 until count) {
            if (getItem(i) == currentChannel) return i
        }
        return -1
    }

    fun sort() {
        this.sort { lhs, rhs ->
            val sortMajor = lhs.majorNumber - rhs.majorNumber
            if (sortMajor == 0) lhs.minorNumber - rhs.minorNumber else sortMajor
        }
    }
}