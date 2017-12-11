package com.gubatenko.comepay.data.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.gubatenko.comepay.R
import com.gubatenko.comepay.module.info.model.Day
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

    fun picture(day: Day): Bitmap {
        return Glide.with(context)
                .asBitmap()
                .load(if (day.main.temp <= 0) "https://hikingartist.files.wordpress.com/2012/05/1-christmas-tree.jpg"
                else "http://assets.smoothradio.com/2013/30/weather-1375260252-article-1.jpg")
                .apply(RequestOptions.centerCropTransform())
                .submit(300, 300)
                .get()
    }

    fun error(): Bitmap {
        val d = ContextCompat.getDrawable(context, R.drawable.unnamed)

        if (d is BitmapDrawable) {
            return d.bitmap
        }

        if (d is GradientDrawable) {
            val bitmap = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            d.setBounds(0, 0, 300, 300)
            d.setStroke(1, Color.BLACK)
            d.isFilterBitmap = true
            d.draw(canvas)
            return bitmap
        }

        val bit = BitmapFactory.decodeResource(context.resources, R.drawable.unnamed)
        return bit.copy(Bitmap.Config.ARGB_8888, true)
    }

}