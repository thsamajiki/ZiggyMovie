package com.hero.ziggymovie.view

import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.hero.ziggymovie.databinding.ActivityMovieDetailWebViewBinding

class MovieDetailWebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailWebViewBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val intent: Intent = intent
//        val url: String = intent.getStringExtra("url")

        binding.movieWebView.webViewClient = MovieWebView()

        val webSet = binding.movieWebView.settings
        webSet.builtInZoomControls = true
        webSet.javaScriptEnabled = true

//        binding.movieWebView.loadUrl(url)
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