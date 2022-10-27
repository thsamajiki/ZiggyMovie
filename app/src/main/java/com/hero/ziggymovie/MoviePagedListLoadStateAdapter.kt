package com.hero.ziggymovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

import com.hero.ziggymovie.databinding.ActivityMainBinding
import com.hero.ziggymovie.databinding.ItemLoadStateBinding

class MoviePagedListLoadStateAdapter(private val retryCallback: () -> Unit):
    LoadStateAdapter<MoviePagedListLoadStateAdapter.LoadStateItemViewHolder>() {

    private lateinit var binding: ItemLoadStateBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateItemViewHolder {
        binding = ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadStateItemViewHolder(private val itemLoadStateBinding: ItemLoadStateBinding) : RecyclerView.ViewHolder(itemLoadStateBinding.root) {
        private lateinit var binding: ActivityMainBinding

        fun bind(loadState: LoadState) {
            itemLoadStateBinding.apply {  }
            if (loadState == LoadState.Loading) {
                binding.pbPopular.visibility = View.VISIBLE
            } else {
                binding.pbPopular.visibility = View.GONE
            }
        }
    }
}