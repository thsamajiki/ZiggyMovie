package com.hero.ziggymovie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.hero.ziggymovie.databinding.ActivityMovieDetailWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailWebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailWebViewBinding

    companion object {
        private const val KEY_URL = "url"

        fun getIntent(context: Context, url: String): Intent =
            Intent(context, MovieDetailWebViewActivity::class.java)
                .putExtra(KEY_URL, url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailWebViewBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val url: String = intent.getStringExtra(KEY_URL).orEmpty()

        binding.movieWebView.webViewClient = MovieWebView()

        val webSet = binding.movieWebView.settings
        webSet.builtInZoomControls = true
        webSet.javaScriptEnabled = true

        binding.movieWebView.loadUrl(url)
    }

    class MovieWebView : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}