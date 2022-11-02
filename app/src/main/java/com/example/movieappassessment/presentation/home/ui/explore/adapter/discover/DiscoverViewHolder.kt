package com.example.movieappassessment.presentation.home.ui.explore.adapter.discover

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.data.remote.dto.DiscoverResultsItem
import com.example.movieappassessment.databinding.ItemViewAllBinding
import com.example.movieappassessment.utils.loadImage

class DiscoverViewHolder(val binding: ItemViewAllBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DiscoverResultsItem) {
        binding.ivImage.loadImage(item.posterPath.toString())
        binding.tvTitle.text = item.title
    }
}