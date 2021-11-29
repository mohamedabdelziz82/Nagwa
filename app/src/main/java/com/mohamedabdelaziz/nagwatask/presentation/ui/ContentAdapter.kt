package com.mohamedabdelaziz.nagwatask.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.mohamedabdelaziz.nagwatask.databinding.ContentItemBinding
import com.mohamedabdelaziz.nagwatask.domain.entities.ContentType
import java.io.File
import javax.inject.Inject

class ContentAdapter @Inject constructor() :
    ListAdapter<Content, ContentAdapter.ContentHolder>(ContentDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentHolder.from(parent)

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ContentDiffCallback : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Content, newItem: Content) = oldItem == newItem
    }

    class ContentHolder(private val binding: ContentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content) {
            binding.content = content
            content.url=content.url.replace("(", "")
            if (content.type == ContentType.VIDEO) {
                SimpleExoPlayer.Builder(binding.root.context)
                    .build()
                    .also { exoPlayer ->
                        binding.videoPlayer.player = exoPlayer
                        exoPlayer.setMediaItem(MediaItem.fromUri(content.url))
                    }
            } else {
                val pdfFilePath =
                    binding.root.context.applicationInfo.dataDir + "/NagwaFiles" + "/${content.id}.pdf"
                val file = File(pdfFilePath)
                if (file.exists()) {
                    loadPdf(file)
                } else {

                    buildPdfDownloader(content.url, pdfFilePath)
                }
            }
        }

        private fun buildPdfDownloader(url: String, pdfFilePath: String) {
            PRDownloader.download(
                url,
                binding.root.context.applicationInfo.dataDir + "/NagwaFiles",
                "${binding.content?.id}.pdf"
            ).build().start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    loadPdf(File(pdfFilePath))
                }

                override fun onError(error: com.downloader.Error?) {
                    Toast.makeText(
                        binding.root.context,
                        "Error in downloading file : ${error?.connectionException}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

        private fun loadPdf(file: File) {
            binding.pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .load()
        }


        companion object {
            fun from(
                parent: ViewGroup,
            ): ContentHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ContentItemBinding.inflate(layoutInflater, parent, false)
                return ContentHolder(binding)
            }
        }
    }
}