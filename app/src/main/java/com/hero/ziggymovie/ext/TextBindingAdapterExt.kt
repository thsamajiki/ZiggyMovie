package com.hero.ziggymovie.ext

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun TextView.setHtmlText(text: String) {
    this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("joinToTextList")
fun TextView.setJoinToTextList(textList: List<String>) {
    this.text = textList.joinToString(", ")
}