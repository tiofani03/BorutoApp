package com.tiooooo.borutoapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tiooooo.borutoapp.data.local.BorutoDatabase
import com.tiooooo.borutoapp.data.remote.api.BorutoApi
import com.tiooooo.borutoapp.data.remote.data_source.RemoteDataSourceImpl
import com.tiooooo.borutoapp.domain.data_source.RemoteDataSource
import com.tiooooo.borutoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createChuckerInterceptor(context))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofitInstance(@ApplicationContext context: Context): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URl)
            .client(provideHttpClient(context))
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    private fun createChuckerInterceptor(context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        return ChuckerInterceptor.Builder(context = context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .build()
    }

    @Provides
    @Singleton
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi {
        return retrofit.create(BorutoApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(borutoApi, borutoDatabase)
    }
}
