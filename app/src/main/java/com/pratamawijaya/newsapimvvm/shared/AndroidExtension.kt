package com.pratamawijaya.newsapimvvm.shared

import android.view.View

fun View.toGone(){
    this.visibility = View.GONE
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}