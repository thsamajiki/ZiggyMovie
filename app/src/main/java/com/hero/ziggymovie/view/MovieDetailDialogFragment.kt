package com.hero.ziggymovie.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.hero.ziggymovie.databinding.FragmentMovieDetailDialogBinding
import com.hero.ziggymovie.view.model.Movie
import com.hero.ziggymovie.view.viewmodel.MovieDetailViewModel


class MovieDetailDialogFragment : DialogFragment() {

    private lateinit var viewModel: MovieDetailViewModel

    private var _binding: FragmentMovieDetailDialogBinding? = null
    private val binding: FragmentMovieDetailDialogBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMovieDetailDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = requireArguments().getParcelable<Movie>(KEY_MOVIE)
        if (movie != null) {
            bindUI(movie)
        }

        binding.tvLinkMovieInfo.setOnClickListener {
            val intent = Intent(requireContext(), MovieDetailWebViewActivity::class.java)
//            intent.putExtra("url")
//            startActivity(intent)
        }
    }

    private fun bindUI(movie: Movie) {
        binding.movie = movie
    }

    companion object {
        private const val KEY_MOVIE = "movie"

        fun newInstance(movie: Movie): MovieDetailDialogFragment =
            MovieDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MOVIE, movie)
                }
            }
    }
}