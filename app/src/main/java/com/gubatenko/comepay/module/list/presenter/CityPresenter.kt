package com.gubatenko.comepay.module.list.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.module.list.model.City

@InjectViewState
class CityPresenter : MvpPresenter<CityView>() {
//    init {
//        InspectorApplication.repoComponent().inject(this)
//    }
//
//    @Inject lateinit var repo: Repository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        content()
    }

    private fun content() {
        viewState.success(listOf(City(), City(), City(), City()))
    }
}