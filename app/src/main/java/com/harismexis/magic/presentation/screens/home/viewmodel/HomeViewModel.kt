package com.harismexis.magic.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.magic.core.repository.MagicLocal
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.core.result.CardsResult
import com.harismexis.magic.framework.event.Event
import com.harismexis.magic.framework.extensions.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    private var searchQuery: String? = null

    fun fetchCards() {
        fetchRemoteCards(searchQuery)
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        fetchRemoteCards(query)
    }

    private fun fetchRemoteCards(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = magicRemote.getCards(name)
                mCardsResult.value = CardsResult.Success(items)
                magicLocal.updateCards(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mCardsResult.value = CardsResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}