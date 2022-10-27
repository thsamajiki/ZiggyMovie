package com.hero.ziggymovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hero.ziggymovie.databinding.ItemMovieListBinding
import com.hero.ziggymovie.view.model.Movie

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
    companion object {
        const val MOVIE_VIEW_TYPE = 1
        const val LOAD_STATE_TYPE = 2
    }
//    private lateinit var binding: ItemMovieListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {

        val binding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieItemViewHolder(binding, onClick)

//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view : View
//
//        if (viewType == MOVIE_VIEW_TYPE) {
//            view = layoutInflater.inflate(R.layout.item_movie_list, parent, false)
//            return MovieItemViewHolder(view)
//        } else {
//            view = layoutInflater.inflate(R.layout.item_load_state, parent, false)
//            return MoviePagedListLoadStateAdapter.LoadStateItemViewHolder(view)
//        }
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
//        if (getItemViewType(position) == MOVIE_VIEW_TYPE) {
//            (holder as MovieItemViewHolder).bind(getItem(position), context)
//        } else {
//            (holder as MoviePagedListLoadStateAdapter.LoadStateItemViewHolder).bind(loadState)
//        }

        getItem(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            MOVIE_VIEW_TYPE
        } else {
            LOAD_STATE_TYPE
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
//            binding = ItemMovieListBinding.bind(itemView)
//            binding.mcvMovieTitle.text = movieData?.title
//            binding.mcvMovieReleaseDate.text = movieData?.releaseDate
//
//            val moviePosterUrl = POSTER_BASE_URL + movieData?.posterPath

//            itemView.setOnClickListener(View.OnClickListener {
//                val intent = Intent(context, MovieDetailActivity::class.java)
//                intent.putExtra("id", movieData?.id)
//                context.startActivity(intent)
//            })
        }
    }

//    class LoadStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private lateinit var binding: ActivityMainBinding
//
//        fun bind(loadStates: LoadStates?) {
//            binding = ActivityMainBinding.bind(itemView)
//            if (loadStates != null && loadStates == LoadStates.LOADING) {
//                binding.pbPopular.visibility = View.VISIBLE
//            } else {
//                binding.pbPopular.visibility = View.GONE
//            }
//
//            if (loadStates != null && loadStates == LoadStates.ERROR) {
//                binding.tvError.visibility = View.VISIBLE
//                binding.tvError.text = loadStates.msg
//            } else if (loadStates != null && loadStates == LoadStates.ENDOFLIST) {
//                binding.tvError.visibility = View.VISIBLE
//                binding.tvError.text = loadStates.msg
//            } else {
//                binding.tvError.visibility = View.GONE
//            }
//        }
//    }
//
//    fun setLoadState(newLoadStates: LoadStates) {
//        val previousState : LoadStates ?= this.loadState
//        val hadExtraRow : Boolean = hasExtraRow()
//        this.loadState = newLoadStates
//        val hasExtraRow : Boolean = hasExtraRow()
//
//        if (hadExtraRow != hasExtraRow) {
//            if (hadExtraRow) {
//                 notifyItemRemoved(super.getItemCount())
//            } else {
//                notifyItemInserted(super.getItemCount())
//            }
//        } else if (hasExtraRow && previousState != loadState) {
//            notifyItemChanged(itemCount - 1)
//        } else {
//
//        }
//    }


}