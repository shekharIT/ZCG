package com.online.zcg.ui.adapter

import com.online.zcg.R
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SplitBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val leftBanner: ImageView = itemView.findViewById(R.id.leftBannerImageView)
    private val rightBanner: ImageView = itemView.findViewById(R.id.rightBannerImageView)

    fun bind(leftBannerUrl: String, rightBannerUrl: String) {
        // Load the left banner
        Glide.with(itemView.context)
            .load(leftBannerUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_placeholder)
            .into(leftBanner)

        // Load the right banner
        Glide.with(itemView.context)
            .load(rightBannerUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_placeholder)
            .into(rightBanner)
    }
}
