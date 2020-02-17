package com.pratamawijaya.newsapimvvm.shared

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun Fragment.toast(msg: String) {
    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}