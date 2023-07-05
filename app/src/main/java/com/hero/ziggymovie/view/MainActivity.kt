package com.hero.ziggymovie.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.databinding.ActivityMainBinding
import com.hero.ziggymovie.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupObserver()
        initRecyclerView(binding.rvMovieList)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        movieListAdapter = MovieListAdapter(
            onClick = ::onClickMovieItem
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return 1 // 1개의 인덱스가 가질 부피
                    }
                }
            }
            adapter = movieListAdapter
        }
        Log.d("initRecyclerView", "initRecyclerView: ${recyclerView.adapter}")
    }

    private fun setupObserver() {
        with(viewModel) {
            movieDataList.observe(this@MainActivity) {
                Log.e("setupObserver", "PagingData<MovieData>: $it")
                movieListAdapter.submitData(lifecycle, it) // ViewModel 에서 전달 받은 PagingData 스트림을 어댑터에 전달
            }
        }
    }

    private fun onClickMovieItem(movieData: MovieData) {
        MovieDetailDialogFragment.newInstance(movieData)
            .show(supportFragmentManager, "MovieDetailDialog")
    }
}