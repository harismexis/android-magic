package com.harismexis.magic.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.magic.core.repository.MagicLocal
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.core.result.cards.CardsResult
import com.harismexis.magic.core.result.sets.SetsResult
import com.harismexis.magic.framework.event.Event
import com.harismexis.magic.framework.extensions.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val magicRemote: MagicRemote,
    private val magicLocal: MagicLocal
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mCardsResult = MutableLiveData<CardsResult>()
    val cardsResult: LiveData<CardsResult>
        get() = mCardsResult

    private val mSetsResult = MutableLiveData<SetsResult>()
    val setsResult: LiveData<SetsResult>
        get() = mSetsResult

    private val mShowError = MutableLiveData<Event<String>>()
    val showError: LiveData<Event<String>>
        get() = mShowError

    private var searchQuery: String? = null

    fun fetchCards() {
        fetchRemoteCards(searchQuery)
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        //fetchAndMakeMainSafe(query)
        fetchAlreadyMainSafe(query)
    }

    private fun fetchAndMakeMainSafe(name: String? = null) {
        // Create a new coroutine with Dispatchers.IO to move
        // the execution off the UI thread. It would be better if
        // the caller did not need to specify Dispatchers and be
        // sure that the function is safe to call in UI Thread (i.e. main safe)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = magicRemote.getCardsBlocking(name)
                // Using postValue cause we are in a background Thread
                mCardsResult.postValue(CardsResult.Success(items))
                magicLocal.updateCards(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mCardsResult.postValue(CardsResult.Error(e))
                mShowError.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchAlreadyMainSafe(name: String? = null) {
        // No need to set Dispatcher, we call the main safe
        // version of getCards
        viewModelScope.launch() {
            try {
                val items = magicRemote.getCardsMainSafe(name)
                // Using value cause we are in UI Thread
                mCardsResult.value = CardsResult.Success(items)
                magicLocal.updateCards(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mCardsResult.value = CardsResult.Error(e)
                mShowError.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchRemoteCards(name: String? = null) {
        // Using Retrofit here, no need to worry for Dispatchers,
        // cause retrofit uses its own, it's always main safe
        viewModelScope.launch {
            try {
                val items = magicRemote.getCards(name)
                mCardsResult.value = CardsResult.Success(items)
                magicLocal.updateCards(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mCardsResult.value = CardsResult.Error(e)
                mShowError.value = Event(e.getErrorMessage())
            }
        }
    }

    fun fetchRemoteSets(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = magicRemote.getSetsMainSafe(name)
                mSetsResult.value = SetsResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mSetsResult.value = SetsResult.Error(e)
                mShowError.value = Event(e.getErrorMessage())
            }
        }
    }

}