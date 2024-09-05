package com.online.zcg.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.online.zcg.R
import com.online.zcg.model.Block

class BlockAdapter(
    private val blocks: List<Block>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_BANNER = 0
        private const val TYPE_HORIZONTAL_FREE_SCROLL = 1
        private const val TYPE_SPLIT_BANNER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (blocks[position].sectionType) {
            "banner" -> TYPE_BANNER
            "horizontalFreeScroll" -> TYPE_HORIZONTAL_FREE_SCROLL
            "splitBanner" -> TYPE_SPLIT_BANNER
            else -> throw IllegalArgumentException("Invalid section type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BANNER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_banner, parent, false)
                BannerViewHolder(view)
            }
            TYPE_HORIZONTAL_FREE_SCROLL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_horizontal_recycleview, parent, false)
                HorizontalFreeScrollViewHolder(view)
            }
            TYPE_SPLIT_BANNER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_split_banner, parent, false)
                SplitBannerViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val block = blocks[position]

        when (holder) {
            is BannerViewHolder -> {
                // Load the banner image from the first item in the list
                val bannerUrl = block.items[0].image
                holder.bind(bannerUrl)
            }
            is HorizontalFreeScrollViewHolder -> {
                // Pass the product list to the horizontal free scroll section
                holder.bind(block.items)
            }
            is SplitBannerViewHolder -> {
                // Load both left and right banner images
                if (block.items.size >= 2) {
                    val leftBannerUrl = block.items[0].image
                    val rightBannerUrl = block.items[1].image
                    holder.bind(leftBannerUrl, rightBannerUrl)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return blocks.size
    }
}
