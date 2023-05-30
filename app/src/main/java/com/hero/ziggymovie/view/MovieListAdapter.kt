package com.hero.ziggymovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.databinding.ItemMovieListBinding

class MovieListAdapter(
    private val onClick: (MovieData) -> Unit
) : PagingDataAdapter<MovieData, MovieListAdapter.MovieItemViewHolder>(
    object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieItemViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    class MovieItemViewHolder(
        private val itemMovieListBinding: ItemMovieListBinding,
        private val onClick: (MovieData) -> Unit
    ) : RecyclerView.ViewHolder(itemMovieListBinding.root) {

        fun bind(item: MovieData) {
            itemMovieListBinding.root.setOnClickListener {
                onClick(item)
            }
            itemMovieListBinding.movie = item
            itemMovieListBinding.executePendingBindings()
        }
    }
}