package com.example.movieappassessment.presentation.home.ui.viewall.adapter.popular

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.databinding.ItemViewAllBinding
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.utils.loadImage

class ViewAllPopularViewHolder(
    val binding: ItemViewAllBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Popular) {
        binding.ivImage.loadImage(item.posterPath.toString())
        binding.tvTitle.text = item.title
    }
}