package com.dkkovalev.product.list.screen

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.dkkovalev.product.list.screen.databinding.ItemProductBinding

class ProductViewHolder(
    private val viewBinding: ItemProductBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: Product) {
        with(viewBinding) {
            productName.text = item.name
            productPrice.text = item.price.toString()
            productPrice.setTextColor(
                when (item.priceState) {
                    PriceState.HIGHER -> Color.RED
                    PriceState.LOWER -> Color.GREEN
                    PriceState.SAME -> Color.GRAY
                }
            )
        }
    }
}