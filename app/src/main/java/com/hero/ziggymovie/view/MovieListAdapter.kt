package com.hero.ziggymovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hero.ziggymovie.data.model.Movie
import com.hero.ziggymovie.databinding.ItemMovieListBinding

class MovieListAdapter(
    private val onClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MovieListAdapter.MovieItemViewHolder>(

    object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
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
        private val onClick: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(itemMovieListBinding.root) {

        fun bind(item: Movie) {
            itemMovieListBinding.root.setOnClickListener {
                onClick(item)
            }
            itemMovieListBinding.movie = item
            itemMovieListBinding.executePendingBindings()
        }
    }
}