package com.example.movieappassessment.domain.adapter.upcoming

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.databinding.ItemMovieBinding
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.utils.date
import com.example.movieappassessment.utils.loadImage

class UpcomingViewHolder(
    val binding: ItemMovieBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Upcoming) {
        with(binding) {
            ivImage.loadImage(item.backdropPath)
            tvName.text = item.title
            tvDate.text = String.format("Initial Date: %s", item.releaseDate.date())
        }
    }
}