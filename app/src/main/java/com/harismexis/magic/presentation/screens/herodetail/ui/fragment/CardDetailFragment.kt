package com.harismexis.magic.presentation.screens.herodetail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.harismexis.magic.R
import com.harismexis.magic.databinding.FragmentHeroDetailBinding
import com.harismexis.magic.databinding.HeroDetailViewBinding
import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.datamodel.result.CardDetailResult
import com.harismexis.magic.framework.extensions.populateWithGlide
import com.harismexis.magic.framework.extensions.setTextOrUnknown
import com.harismexis.magic.framework.extensions.showToast
import com.harismexis.magic.presentation.base.BaseFragment
import com.harismexis.magic.presentation.screens.herodetail.viewmodel.CardDetailViewModel

class CardDetailFragment : BaseFragment() {

    private var binding: FragmentHeroDetailBinding? = null
    private var detailBinding: HeroDetailViewBinding? = null
    private val viewModel: CardDetailViewModel by viewModels()

    companion object {
        private const val ARG_CARD_ID = "cardId"

        fun newInstance(cardId: Int): CardDetailFragment {
            val args = Bundle()
            args.putInt(ARG_CARD_ID, cardId)
            val fragment = CardDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated() {
        setupToolbar()
        observeLiveData()
        fetchHeroDetails()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.toolbar?.setupWithNavController(navController, appBarConf)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        detailBinding = binding?.itemDetailContainer
    }

    override fun getRootView() = binding?.root

    private fun observeLiveData() {
        viewModel.cardDetailResult.observe(viewLifecycleOwner, {
            when (it) {
                is CardDetailResult.Success -> populate(it.item)
                is CardDetailResult.Error -> populateError(it.error)
            }
        })
    }

    private fun populateError(error: String) {
        requireContext().showToast(error)
    }

    override fun onCreateView() {}

    private fun fetchHeroDetails() {
        val heroId = arguments?.getInt(ARG_CARD_ID)
        heroId?.let {
            viewModel.getHeroById(it)
        }
    }

    private fun populate(card: Card) {
        binding?.let {
            it.toolbarTitle.text = card.name
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
        }
        detailBinding?.let {
            requireContext().populateWithGlide(it.img, card.imageUrl)
            it.txtName.setTextOrUnknown(card.name)
            it.txtType.setTextOrUnknown(card.type)
        }
    }

}