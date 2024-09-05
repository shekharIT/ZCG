package com.online.zcg.ui.adapter

import com.online.zcg.R
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bannerImageView: ImageView = itemView.findViewById(R.id.bannerImageView)

    fun bind(bannerUrl: String?) {
        if (bannerUrl != null) {
            // Load the banner URL into image view
            loadBanner(bannerUrl)
        } else {
            // Handle the null case, e.g., load a placeholder image
            loadPlaceholderBanner()
        }
    }

    private fun loadBanner(bannerUrl: String) {
        Glide.with(itemView.context)
            .load(bannerUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_placeholder)
            .into(bannerImageView)
    }

    private fun loadPlaceholderBanner() {
        Glide.with(itemView).load(R.drawable.placeholder).into(bannerImageView)
    }
}

