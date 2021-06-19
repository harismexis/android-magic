package com.harismexis.magic.presentation.screens.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.magic.databinding.VhHeroItemBinding
import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.presentation.screens.home.ui.viewholder.HeroViewHolder

class HomeAdapter(
    private val models: List<Card>,
    private val clickListener: HeroViewHolder.HeroClickListener
) : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroViewHolder {
        return HeroViewHolder(
            VhHeroItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: HeroViewHolder,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: HeroViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}