package com.gubatenko.comepay.module.info.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gubatenko.comepay.Const
import com.gubatenko.comepay.R
import com.gubatenko.comepay.module.info.model.CityInfo
import com.gubatenko.comepay.module.info.presenter.CityInfoPresenter
import com.gubatenko.comepay.module.info.presenter.CityInfoView
import kotlinx.android.synthetic.main.fragment_city.*

class CityInfoFragment : MvpAppCompatDialogFragment(), CityInfoView {
    @InjectPresenter lateinit var presenter: CityInfoPresenter

    @ProvidePresenter
    fun presenter(): CityInfoPresenter = CityInfoPresenter(arguments?.getString(Const.CITY_ID, ""))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_city, container, false)

    override fun success(item: CityInfo) {
        retry.visibility = View.GONE
        info.visibility = View.GONE
        picture.setImageBitmap(item.picture)
        description.text = item.description
        name.text = arguments?.getString(Const.CITY_NAME, "")
    }

    override fun progress(visibility: Int) {
        retry.visibility = View.GONE
        info.visibility = View.GONE
        progress.visibility = visibility
    }

    override fun error(message: String) {
        retry.visibility = View.VISIBLE
        info.visibility = View.VISIBLE
        info.text = message
    }

    companion object {

        fun instance(id: String, name: String): CityInfoFragment {
            val fragment = CityInfoFragment()
            val bundle = Bundle()
            bundle.putString(Const.CITY_ID, id)
            bundle.putString(Const.CITY_NAME, name)
            fragment.arguments = bundle
            return fragment
        }

    }
}