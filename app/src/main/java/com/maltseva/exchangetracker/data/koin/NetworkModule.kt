package com.maltseva.exchangetracker.data.koin

import com.maltseva.exchangetracker.data.ExchangeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://openexchangerates.org/api/"

fun provideHttpClient(): OkHttpClient {
    val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideService(retrofit: Retrofit): ExchangeApi =
    retrofit.create(ExchangeApi::class.java)

val networkModule = module {
    single { provideHttpClient() }
    single { provideRetrofit(get()) }
    single { provideService(get()) }
}