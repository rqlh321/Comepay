package com.gubatenko.comepay.module.list.ui

import android.view.View
import com.gubatenko.comepay.module.list.presenter.CityPresenter
import com.gubatenko.comepay.R
import com.gubatenko.comepay.base.BaseAdapter
import com.gubatenko.comepay.module.list.model.City

class CityAdapter(presenter: CityPresenter) : BaseAdapter<City, CityPresenter, CityViewHolder>(presenter) {

    override fun getViewType(position: Int): Int = R.layout.list_item_city

    override fun vhProvider(view: View, viewType: Int): CityViewHolder = CityViewHolder(view)

}
