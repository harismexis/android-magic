package com.harismexis.magic.presentation.screens.carddetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.magic.core.repository.MagicLocal
import com.harismexis.magic.core.result.cards.CardDetailResult
import com.harismexis.magic.framework.extensions.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailViewModel @Inject constructor(
    private val magicLocal: MagicLocal,
) : ViewModel() {

    private val tag = CardDetailViewModel::class.qualifiedName

    private val mCardDetailResult = MutableLiveData<CardDetailResult>()
    val cardDetailResult: LiveData<CardDetailResult>
        get() = mCardDetailResult

    fun getHeroById(id: String) {
        viewModelScope.launch {
            try {
                val item = magicLocal.getCard(id)
                item?.let {
                    mCardDetailResult.value = CardDetailResult.Success(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mCardDetailResult.value = CardDetailResult.Error(e.getErrorMessage())
            }
        }
    }

}
