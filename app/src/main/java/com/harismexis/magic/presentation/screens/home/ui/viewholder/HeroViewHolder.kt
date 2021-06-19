package com.harismexis.magic.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.magic.databinding.VhHeroItemBinding
import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.framework.extensions.populateWithGlide

class HeroViewHolder(
    private val binding: VhHeroItemBinding,
    private val itemClick: HeroClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface HeroClickListener {
        fun onHeroClick(item: Card, position: Int)
    }

    fun bind(
        item: Card,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgView, item.imageUrl)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.type
        itemView.setOnClickListener {
            itemClick.onHeroClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}