package com.harismexis.magic.presentation.screens.herodetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.magic.datamodel.repository.MagicLocal
import com.harismexis.magic.datamodel.result.CardDetailResult
import com.harismexis.magic.framework.extensions.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailViewModel @Inject constructor(
    private val magicLocal: MagicLocal,
) : ViewModel() {

    private val tag = CardDetailViewModel::class.qualifiedName

    private val mHeroDetailResult = MutableLiveData<CardDetailResult>()
    val cardDetailResult: LiveData<CardDetailResult>
        get() = mHeroDetailResult

    fun getHeroById(id: String) {
        viewModelScope.launch {
            try {
                val item = magicLocal.getHero(id)
                item?.let {
                    mHeroDetailResult.value = CardDetailResult.Success(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mHeroDetailResult.value = CardDetailResult.Error(e.getErrorMessage())
            }
        }
    }

}
