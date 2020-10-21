package com.enzoftware.randomuser.di

import android.content.Context
import com.enzoftware.randomuser.data.network.RandomUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    companion object {
        const val CACHE_SIZE: Long = 5 * 1024 * 1024
        const val TIMEOUT: Long = 10
    }

    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient().newBuilder()
            .cache(cache)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me")
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory).build()


    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    fun providesMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun providesComposeNewsService(retrofit: Retrofit): RandomUserService =
        retrofit.create(RandomUserService::class.java)
}