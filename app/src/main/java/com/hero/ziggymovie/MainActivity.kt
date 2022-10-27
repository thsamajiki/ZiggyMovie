package com.hero.ziggymovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hero.ziggymovie.databinding.ActivityMainBinding
import com.hero.ziggymovie.view.MovieDetailDialogFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

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
        binding.viewModel = getViewModel()
        val view = binding.root
        setContentView(view)

        viewModel = getViewModel()

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType : Int = movieListAdapter.getItemViewType(position)

                if (viewType == MovieListAdapter.MOVIE_VIEW_TYPE) {
                    return 1
                } else {
                    return 3
                }
            }
        }

        binding.rvMovieList.layoutManager = gridLayoutManager
        binding.rvMovieList.setHasFixedSize(true)
        binding.rvMovieList.adapter = movieListAdapter

        setUpRecyclerView()
        setupObserver()
    }

    override fun onClick(view: View?) {
        when(view?.id) {

        }
    }

    private fun setUpRecyclerView() {
        binding.rvMovieList.apply {
            layoutManager = GridLayoutManager(baseContext, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val viewType : Int = movieListAdapter.getItemViewType(position)

                        return when (viewType) {
                            MovieListAdapter.MOVIE_VIEW_TYPE -> {
                                3
                            }
                            MovieListAdapter.LOAD_STATE_TYPE -> {
                                1
                            }
                            else -> {
                                0
                            }
                        }
                    }
                }
            }
            adapter = movieListAdapter
        }
    }

    private fun setupObserver() {
        with(viewModel) {
            movieList.observe(this@MainActivity) {
                movieListAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun getViewModel(): MainViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(Injector.provideMovieRepository()) as T
            }
        })[MainViewModel::class.java]
    }
}