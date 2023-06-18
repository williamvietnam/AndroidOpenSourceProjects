package com.android.apps.appVideoShorts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.databinding.ItemVideoShortBinding

class VideoShortsAdapter(
    val data: MutableList<VideoShortItem>
) : RecyclerView.Adapter<VideoShortsAdapter.VideoShortHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoShortHolder {
        val binding = ItemVideoShortBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VideoShortHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoShortHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class VideoShortHolder(
        private val binding: ItemVideoShortBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val video = data[position]
            binding.tvVideoTitle.text = video.videoTitle
            binding.tvVideoDescription.text = video.videoDescription
            binding.videoView.setVideoPath(video.videoURL)
            binding.videoView.setOnPreparedListener {
                binding.videoProgressBar.visibility = View.GONE
                it.start()
                val videoRatio: Float = (it.videoWidth.toFloat() / it.videoHeight.toFloat())
                val screenRatio: Float =
                    binding.videoView.width.toFloat() / binding.videoView.height.toFloat()
                val scale: Float = videoRatio / screenRatio
                if (scale >= 1f) {
                    binding.videoView.scaleX = scale
                } else {
                    binding.videoView.scaleY = (1f / scale)
                }
            }
            binding.videoView.setOnCompletionListener {
                it.start()
            }
        }
    }
}