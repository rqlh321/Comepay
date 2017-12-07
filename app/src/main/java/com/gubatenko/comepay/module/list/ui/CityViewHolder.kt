package com.gubatenko.comepay.module.list.ui

import android.view.View
import com.gubatenko.comepay.module.list.presenter.CityPresenter
import com.gubatenko.comepay.base.BaseViewHolder
import com.gubatenko.comepay.module.list.model.City
import kotlinx.android.synthetic.main.list_item_city.view.*

class CityViewHolder(itemView: View) : BaseViewHolder<City, CityPresenter>(itemView) {

    override fun bind(item: City, presenter: CityPresenter) {
        itemView.name.text = item.name
        itemView.setOnClickListener { presenter.viewState.show(item) }
    }

}