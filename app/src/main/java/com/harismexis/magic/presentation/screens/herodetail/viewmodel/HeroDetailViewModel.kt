package com.harismexis.magic.presentation.screens.herodetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.magic.datamodel.repository.HeroLocal
import com.harismexis.magic.framework.extensions.getErrorMessage
import com.harismexis.magic.datamodel.result.HeroDetailResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val heroLocal: HeroLocal,
) : ViewModel() {

    private val tag = HeroDetailViewModel::class.qualifiedName

    private val mHeroDetailResult = MutableLiveData<HeroDetailResult>()
    val heroDetailResult: LiveData<HeroDetailResult>
        get() = mHeroDetailResult

    fun getHeroById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = heroLocal.getHero(itemId)
                item?.let {
                    mHeroDetailResult.value = HeroDetailResult.Success(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mHeroDetailResult.value = HeroDetailResult.Error(e.getErrorMessage())
            }
        }
    }

}
