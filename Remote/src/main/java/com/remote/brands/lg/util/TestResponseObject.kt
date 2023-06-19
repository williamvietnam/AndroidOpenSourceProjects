package com.android.remotes.lg.util

class TestResponseObject {
    var isSuccess: Boolean
    var httpResponseCode: Int
    var responseMessage: String

    constructor() : super() {
        isSuccess = false
        httpResponseCode = 0
        responseMessage = "default"
    }

    constructor(isSuccess: Boolean, httpResponseCode: Int, responseMessage: String) : super() {
        this.isSuccess = isSuccess
        this.httpResponseCode = httpResponseCode
        this.responseMessage = responseMessage
    }

    companion object {
        const val Default = "default"
        const val SuccessCode = 200
        const val Display_image = "ImageLaunched"
        const val Play_Video = "VideoLaunched"
        const val Play_Audio = "AudioLaunched"
        const val Closed_Media = "MediaClosed"
        const val Stopped_Media = "MediaStop"
        const val Stopped_image = "ImageStopped"
        const val Paused_Media = "MediaPaused"
        const val Played_Media = "MediaPlayed"
        const val Rewind_Media = "MediaRewind"
        const val FastForward_Media = "MediaFastForward"
        const val Launched_Netflix = "NetflixLaunched"
        const val Launched_Browser = "BrowserLaunched"
        const val Launched_Youtube = "YoutubeLaunched"
        const val Launched_WebAPP = "WebAPPLaunched"
        const val Joined_WebAPP = "WebAPPJoined"
        const val Pinned_WebAPP = "WebAPPPinned"
        const val UnPinned_WebAPP = "WebAPPUnPinned"
        const val Sent_Message = "SentMessage"
        const val Sent_JSON = "SentJSON"
        const val Leave_WebAPP = "LeaveWebAPP"
        const val Close_WebAPP = "ClosedWebAPP"
        const val Power_OFF = "PowerOFF"
        const val Show_Toast = "ShowedToast"
        const val Mute_Toggle = "MuteToggle"
        const val Muted_Media = "MuteMedia"
        const val UnMuted_Media = "UnMuteMedia"
        const val VolumeUp = "VolumeIncreased"
        const val VolumeDown = "VolumeDecreased"
        const val InputPickerVisible = "InputPickerShowing"
        const val HomeClicked = "HomeClicked"
        const val LeftClicked = "LeftClicked"
        const val RightClicked = "RightClicked"
        const val UpClicked = "UpClicked"
        const val DownClicked = "DownClicked"
        const val Clicked = "Clicked"
        const val Played_Playlist = "PlayedPlaylist"
        const val Jump = "jumpedTrack"
        const val Previous = "PreviousClicked"
        const val Next = "NextClicked"
    }
}
