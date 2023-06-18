package com.android.apps.appVideoShorts

import com.android.commons.base.BaseViewModel

class VideoShortsViewModel : BaseViewModel() {
    fun initializeData(): MutableList<VideoShortItem> {
        val data = ArrayList<VideoShortItem>()

        val videoShort1 = VideoShortItem(
            videoURL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            videoTitle = "",
            videoDescription = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\\n\\nLicensed under the Creative Commons Attribution license\\nhttp://www.bigbuckbunny.org"
        )
        data.add(videoShort1)

        val videoShort2 = VideoShortItem(
            videoURL = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            videoTitle = "",
            videoDescription = ""
        )
        data.add(videoShort2)

        val videoShort3 = VideoShortItem(
            videoURL = "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            videoTitle = "",
            videoDescription = ""
        )
        data.add(videoShort3)


        val videoShort4 = VideoShortItem(
            videoURL = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
            videoTitle = "",
            videoDescription = ""
        )
        data.add(videoShort4)

        return data
    }
}