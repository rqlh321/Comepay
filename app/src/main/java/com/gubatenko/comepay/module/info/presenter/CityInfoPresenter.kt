package com.gubatenko.comepay.module.info.presenter

import android.graphics.Bitmap
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.module.info.model.CityInfo

@InjectViewState
class CityInfoPresenter(private val id: String?) : MvpPresenter<CityInfoView>() {
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
        val picture = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_4444)
        val item = CityInfo(picture)
        viewState.success(item)
    }
}