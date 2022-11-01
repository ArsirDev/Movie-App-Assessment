package com.example.movieappassessment.domain.adapter.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.databinding.ItemMovieBinding
import com.example.movieappassessment.domain.model.Upcoming

class UpcomingAdapter: RecyclerView.Adapter<UpcomingViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Upcoming>(){
        override fun areItemsTheSame(
            oldItem: Upcoming,
            newItem: Upcoming
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Upcoming,
            newItem: Upcoming
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        fun instance() = UpcomingAdapter()
    }
}