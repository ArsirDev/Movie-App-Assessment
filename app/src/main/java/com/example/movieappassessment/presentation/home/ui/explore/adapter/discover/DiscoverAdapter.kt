package com.example.movieappassessment.presentation.home.ui.explore.adapter.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.data.remote.dto.DiscoverResultsItem
import com.example.movieappassessment.databinding.ItemViewAllBinding
import com.example.movieappassessment.utils.setOnClickListenerWithDebounce

class DiscoverAdapter: RecyclerView.Adapter<DiscoverViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DiscoverResultsItem>() {
        override fun areItemsTheSame(
            oldItem: DiscoverResultsItem,
            newItem: DiscoverResultsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DiscoverResultsItem,
            newItem: DiscoverResultsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        return DiscoverViewHolder(ItemViewAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
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
        fun instance() = DiscoverAdapter()
    }
}