package com.hero.ziggymovie.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hero.ziggymovie.databinding.ActivityMainBinding
import com.hero.ziggymovie.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private val movieListAdapter : MovieListAdapter by lazy {
        MovieListAdapter(
            onClick = { movie ->
                MovieDetailDialogFragment.newInstance(movie)
                    .show(supportFragmentManager, "MovieDetailDialog")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val view = binding.root
        setContentView(view)

        initRecyclerView(binding.rvMovieList)
        setupObserver()
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(baseContext, 3).apply {
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
            movieList.observe(this@MainActivity) {
                movieListAdapter.submitData(lifecycle, it) // ViewModel 에서 전달받은 PagingData 스트림을 어댑터에 전달
            }
        }
    }

    override fun onClick(view: View) {
    }
}