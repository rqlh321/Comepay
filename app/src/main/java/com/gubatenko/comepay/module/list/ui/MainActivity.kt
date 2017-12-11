package com.gubatenko.comepay.module.list.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.gubatenko.comepay.R
import com.gubatenko.comepay.module.info.ui.CityInfoFragment
import com.gubatenko.comepay.module.list.model.City
import com.gubatenko.comepay.module.list.presenter.CityPresenter
import com.gubatenko.comepay.module.list.presenter.CityView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : MvpAppCompatActivity(), CityView {
    @InjectPresenter lateinit var presenter: CityPresenter

    private lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        list.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        list.layoutManager = linearLayoutManager
        list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        adapter = CityAdapter(presenter)
        list.adapter = adapter

        retry.setOnClickListener { presenter.content() }
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

    override fun success(list: List<City>) {
        retry.visibility = View.GONE
        info.visibility = View.GONE
        adapter.addAll(list)
    }

    override fun show(item: City) {
        CityInfoFragment.instance(item.id, item.name).show(supportFragmentManager, MainActivity::class.java.name)
    }

}
