package com.example.movieappassessment.domain.adapter.popular

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.databinding.ItemMovieBinding
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.utils.date
import com.example.movieappassessment.utils.loadImage

class PopularViewHolder(
    val binding: ItemMovieBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Popular) {
        with(binding) {
            ivImage.loadImage(item.backdropPath)
            tvName.text = item.title
            tvDate.text = String.format("Initial Date: %s", item.releaseDate.date())
        }
    }
}