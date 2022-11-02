package com.example.movieappassessment.presentation.home.ui.explore.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.data.remote.dto.SearchResultsItem
import com.example.movieappassessment.databinding.ItemViewAllBinding
import com.example.movieappassessment.utils.loadImage

class ExploreViewHolder(
    val binding: ItemViewAllBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SearchResultsItem) {
        binding.ivImage.loadImage(item.posterPath.toString())
        binding.tvTitle.text = item.title
    }
}