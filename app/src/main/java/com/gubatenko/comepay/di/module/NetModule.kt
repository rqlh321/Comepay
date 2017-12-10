package com.gubatenko.comepay.di.module

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gubatenko.comepay.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetModule(private val serverAddress: String, private val apiKey: String) {

    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        clientBuilder.addInterceptor(logging)
        clientBuilder.cache(cache)
        clientBuilder.connectTimeout(10, TimeUnit.SECONDS)
        clientBuilder.readTimeout(0, TimeUnit.SECONDS)
        clientBuilder.addInterceptor({ chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .headers(original.headers())
                    .header("X-Yandex-API-Key", apiKey)
                    .method(original.method(), original.body())
                    .build()

            chain.proceed(request)
        })
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(gson: Gson, okHttpClient: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(serverAddress)
                .client(okHttpClient)
                .build()
                .create(WeatherApi::class.java)
    }


}