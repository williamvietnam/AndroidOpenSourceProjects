package com.android.common.player

import android.content.Context
import android.media.MediaPlayer

class PlayerManager {

    companion object {
        @JvmStatic
        val shared = PlayerManager()
    }

    private var player: MediaPlayer? = null
    private var isLoop: Boolean = false

    fun getMediaPlayerInstance(): MediaPlayer? {
        if (player == null) {
            player = MediaPlayer()
        }
        return this@PlayerManager.player
    }

    fun play(name: String, context: Context) {
        try {
            if (player?.isPlaying == true) {
                player?.stop()
                player?.reset()
            }
            player = MediaPlayer()
            val descriptor = context.assets.openFd("mp3/$name")
            player?.setDataSource(
                descriptor.fileDescriptor,
                descriptor.startOffset,
                descriptor.length
            )
            descriptor.close()
            player?.prepare()
            player?.setVolume(1f, 1f)
            player?.start()
            player?.isLooping = isLoop
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun play(name: String, isLoop: Boolean, context: Context) {
        try {
            if (player?.isPlaying == true) {
                player?.stop()
                player?.reset()
            }
            player = MediaPlayer()
            val descriptor = context.assets.openFd("mp3/$name")
            player?.setDataSource(
                descriptor.fileDescriptor,
                descriptor.startOffset,
                descriptor.length
            )
            descriptor.close()
            player?.prepare()
            player?.setVolume(1f, 1f)
            player?.isLooping = isLoop
            player?.start()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun pause() {
        try {
            player?.pause()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    fun stop() {
        player?.stop()
        player?.reset()
    }

    fun loop(isLoop: Boolean) {
        this.isLoop = isLoop
    }
}