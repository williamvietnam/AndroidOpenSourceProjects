package com.android.apps.appVideoShorts

import android.net.Uri
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
            val uri = Uri.parse(video.videoURL)
            binding.videoView.setVideoURI(uri)
            binding.videoView.setOnPreparedListener {
                binding.videoProgressBar.visibility = View.GONE
                it.start()
            }
            binding.videoView.setOnCompletionListener {
                it.start()
            }
        }
    }
}