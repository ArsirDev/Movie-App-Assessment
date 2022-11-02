package com.example.movieappassessment.presentation.home.ui.explore.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.data.remote.dto.SearchResultsItem
import com.example.movieappassessment.databinding.ItemViewAllBinding
import com.example.movieappassessment.utils.setOnClickListenerWithDebounce

class ExploreAdapter: RecyclerView.Adapter<ExploreViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<SearchResultsItem>() {
        override fun areItemsTheSame(
            oldItem: SearchResultsItem,
            newItem: SearchResultsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchResultsItem,
            newItem: SearchResultsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(ItemViewAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.apply {
            bind(differ.currentList[position].also { item ->
                itemView.setOnClickListenerWithDebounce {
                    onItemClickListener?.let { id ->
                        id(item.id)
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        fun instance() = ExploreAdapter()
    }
}