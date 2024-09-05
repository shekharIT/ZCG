package com.online.zcg.utils

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

fun View.asButtonHideShow(view: Button, flag: Boolean) {
    if (flag) {
        this.visibility = View.VISIBLE
        view.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
        view.visibility = View.GONE
    }
}

fun View.asTextHideShow(view: TextView, flag: Boolean) {
    if (flag) {
        this.visibility = View.VISIBLE
        view.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
        view.visibility = View.GONE
    }
}

fun View.asRecyclerViewHideShow(view: RecyclerView, flag: Boolean) {
    if (flag) {
        this.visibility = View.VISIBLE
        view.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
        view.visibility = View.GONE
    }
}

fun View.asProgressBarHideShow(view: ProgressBar, flag: Boolean) {
    if (flag) {
        this.visibility = View.VISIBLE
        view.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
        view.visibility = View.GONE
    }
}