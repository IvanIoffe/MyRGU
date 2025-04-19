package com.application.myrgu.core.data.source.remote.di

import com.application.myrgu.core.utils.network.ConnectionObserver
import com.application.myrgu.core.utils.network.NetworkConnectionInterceptor
import com.application.myrgu.core.utils.network.NetworkConnectionObserver
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [NetworkModuleProvider::class, NetworkModuleBinder::class])
class NetworkModule

@Module
class NetworkModuleProvider {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        val baseUrl = "http://91.203.180.183:3000/"
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }
}

@Module
interface NetworkModuleBinder {

    @Binds
    fun bindNetworkConnectionObserver(
        networkConnectionObserver: NetworkConnectionObserver,
    ): ConnectionObserver
}