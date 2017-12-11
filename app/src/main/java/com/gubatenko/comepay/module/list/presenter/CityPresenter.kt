package com.gubatenko.comepay.module.list.presenter

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.CompeyApplication
import com.gubatenko.comepay.data.local.Res
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class CityPresenter : MvpPresenter<CityView>() {
    init {
        CompeyApplication.netComponent().inject(this)
    }

    @Inject lateinit var res: Res

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        content()
    }

    fun content() {
        res.cites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progress(View.VISIBLE) }
                .doOnError { viewState.progress(View.GONE) }
                .doOnSuccess { viewState.progress(View.GONE) }
                .subscribe({ viewState.success(it) }, { viewState.error(it.message.toString()) })
    }
}