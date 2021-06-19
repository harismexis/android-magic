package com.harismexis.magic.presentation.screens.home.ui.fragment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.harismexis.magic.R
import com.harismexis.magic.databinding.FragmentHomeBinding
import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.datamodel.result.CardsResult
import com.harismexis.magic.framework.event.EventObserver
import com.harismexis.magic.framework.extensions.showToast
import com.harismexis.magic.framework.util.ui.hideKeyboard
import com.harismexis.magic.presentation.base.BaseFragment
import com.harismexis.magic.presentation.screens.home.ui.adapter.HomeAdapter
import com.harismexis.magic.presentation.screens.home.ui.viewholder.HeroViewHolder
import com.harismexis.magic.presentation.screens.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment(), HeroViewHolder.HeroClickListener,
    android.widget.SearchView.OnQueryTextListener,
    NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val MAGIC_API_WEBSITE = "https://docs.magicthegathering.io/"
    }

    val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter
    private var uiModels: MutableList<Card> = mutableListOf()

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
        initialiseSearchView()
    }

    private fun setupSwipeToRefresh() {
        binding?.homeSwipeRefresh?.setOnRefreshListener {
            binding?.homeSwipeRefresh?.isRefreshing = true
            viewModel.fetchHeros()
        }
    }

    private fun initialiseRecycler() {
        adapter = HomeAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding?.homeList?.layoutManager = LinearLayoutManager(this.context)
        binding?.homeList?.adapter = adapter
    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchHeros()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph, binding?.homeDrawerLayout)
        binding?.apply { ->
            toolbar.setupWithNavController(navController, appBarConf)
            toolbar.inflateMenu(R.menu.menu_home)
            // Without listener it's not working, but it should(?)
            // as we call setupWithNavController
            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    else -> false
                }
            }
            navView.setupWithNavController(navController)
            navView.setNavigationItemSelectedListener(this@HomeFragment)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.doc_dest -> {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(MAGIC_API_WEBSITE)
                )
                startActivity(browserIntent)
            }
            else -> item.onNavDestinationSelected(findNavController())
        }
        closeDrawer()
        return true
    }

    private fun closeDrawer() {
        binding?.homeDrawerLayout?.closeDrawer(GravityCompat.START)
    }

    private fun observeLiveData() {
        viewModel.cardsResult.observe(viewLifecycleOwner, {
            when (it) {
                is CardsResult.Success -> populate(it.items)
                is CardsResult.Error -> populateError(it.error)
            }
        })

        viewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            requireContext().showToast(it)
        })
    }

    private fun populate(models: List<Card>) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        binding?.homeList?.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun populateError(error: Exception) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
    }

    override fun onHeroClick(
        item: Card,
        position: Int
    ) {
        binding?.searchView?.clearFocus()
        val action = HomeFragmentDirections.actionHomeDestToHeroDetailDest(item.id)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        requireActivity().hideKeyboard()
        viewModel.updateSearchQuery(query)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    override fun getRootView() = binding?.root

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}