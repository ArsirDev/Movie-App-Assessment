package com.example.movieappassessment.presentation.home.ui.viewall.adapter.upcoming

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.databinding.ItemViewAllBinding
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.utils.loadImage

class ViewAllUpcomingViewHolder(val binding: ItemViewAllBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Upcoming) {
        binding.ivImage.loadImage(item.posterPath.toString())
        binding.tvTitle.text = item.title
    }
}