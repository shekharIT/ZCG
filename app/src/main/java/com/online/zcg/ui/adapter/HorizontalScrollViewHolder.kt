package com.online.zcg.ui.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.online.zcg.R
import com.online.zcg.model.Product

class HorizontalFreeScrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)

    fun bind(products: List<Product>) {
        val productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }
}
