package com.hero.ziggymovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.databinding.FragmentMovieDetailDialogBinding
import com.hero.ziggymovie.view.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailDialogFragment : DialogFragment() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private var _binding: FragmentMovieDetailDialogBinding? = null
    private val binding: FragmentMovieDetailDialogBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindUI(viewModel.movieData)

//        binding.tvLinkMovieInfo.setOnClickListener {
//            val intent = MovieDetailWebViewActivity.getIntent(requireContext(), viewModel.movie.)
//            startActivity(intent)
//        }
    }

    private fun bindUI(movieData: MovieData) {
        binding.movie = movieData
    }

    companion object {
        fun newInstance(movieData: MovieData): MovieDetailDialogFragment =
            MovieDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MovieDetailViewModel.KEY_MOVIE, movieData)
                }
            }
    }
}