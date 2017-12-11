package com.gubatenko.comepay.data.local

import android.content.Context
import com.google.gson.Gson
import com.gubatenko.comepay.R
import com.gubatenko.comepay.module.list.model.City
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

class Res(private val context: Context) {

    private val gson = Gson()

    fun server(): String = context.getString(R.string.server)

    fun cites(): Single<List<City>> {
        return Single.fromCallable {
            val stream = context.resources.openRawResource(R.raw.cites)
            val writer = StringWriter()
            val buffer = CharArray(1024)

            stream.use {
                val reader = BufferedReader(InputStreamReader(it, "UTF-8"))
                var n: Int = reader.read(buffer)
                while (n != -1) {
                    writer.write(buffer, 0, n)
                    n = reader.read(buffer)
                }
            }

            writer.toString()
        }
                .observeOn(Schedulers.newThread())
                .map {
                    gson.fromJson(it, Array<City>::class.java).asList()
                }
    }


}