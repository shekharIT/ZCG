package com.online.zcg.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.zcg.R
import com.online.zcg.databinding.ItemHorizontalScrollBinding
import com.online.zcg.model.Product

class ProductAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemHorizontalScrollBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            // Load the product image using Glide
            Glide.with(binding.root.context)
                .load(product.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_placeholder)
                .into(binding.productImageView)

            // Set the product title
            binding.productTitleTextView.text = product.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemHorizontalScrollBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
