package com.example.randomquote.di

import android.app.Application
import com.example.randomquote.utils.Constants
import com.example.randomquote.utils.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePreferenceManager(application: Application): PreferenceManager =
        PreferenceManager(application)

    @Singleton
    @Provides
    fun provideHttpClient(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(
                        "Access-Control-Allow-Origin",
                        "api"
                    )
                    .addHeader(
                        "APPKEY",
                        "AAAA01g3ydw:APA91bEbx53P3ItBerUV5oDviGfI3IdKIpzJCBEeFk0iuyrUyPr-9X6QqDl_h6OJxUbQAJiv8gik_UGJiVrbH7eQLUj59w_RCCwzs_Rgp-Dt3FsyYx3gs4gJ50T7XP62Zhkic73a6mbu"
                    )
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

}