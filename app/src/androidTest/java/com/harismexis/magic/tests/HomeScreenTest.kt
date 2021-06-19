package com.harismexis.magic.tests

import androidx.lifecycle.MutableLiveData
import com.harismexis.magic.datamodel.domain.Hero
import com.harismexis.magic.presentation.result.HerosResult
import com.harismexis.magic.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.magic.setup.base.InstrumentedTestSetup
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import org.junit.Rule

@HiltAndroidTest
class HomeScreenTest: InstrumentedTestSetup() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    var mockViewModel : HomeViewModel = mockk(relaxed = true)
    var fakeHerosResult = MutableLiveData<HerosResult>()
    private lateinit var mockHeros: List<Hero>
    private lateinit var herosSuccess: HerosResult.Success



}