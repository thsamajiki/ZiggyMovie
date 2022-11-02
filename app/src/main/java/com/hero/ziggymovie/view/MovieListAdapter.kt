package com.hero.ziggymovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hero.ziggymovie.data.model.Movie
import com.hero.ziggymovie.databinding.ItemMovieListBinding

// PagingDataAdapter
// PagingData를 입력받아 내부적으로 언제 데이터를 추가해야 할지 관찰한다.
// PagingDataAdapter는 백그라운드 스레드에서 DiffUtil을 사용하여 데이터를 정제한 뒤에 데이터를 로드하기 때문에
// UI 스레드에서 새로운 항목을 추가할 때 부드럽게 나타낼 수 있습니다.
class MovieListAdapter(
    private val onClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MovieListAdapter.MovieItemViewHolder>(
    // DiffUtil 클래스
    // 이전 데이터 상태와 현재 데이터간의 상태 차이를 계산하고, 반드시 업데이트해야 할 최소한의 데이터에 대해서만 갱신하게 된다.
    // 데이터 업데이트 횟수를 최소한으로 가져가는 것이라 보면 된다.
    object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean { // 두 객체가 동일한 항목을 나타내는지 확인
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean { // 두 항목의 데이터가 같은지 확인
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
            itemMovieListBinding.executePendingBindings() // 바인딩을 즉시 실행해야 하므로.
        }
    }
}