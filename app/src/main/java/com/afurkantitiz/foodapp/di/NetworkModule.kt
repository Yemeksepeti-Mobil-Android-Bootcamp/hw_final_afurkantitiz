package com.afurkantitiz.foodapp.di

import com.afurkantitiz.foodapp.data.local.LocalDataSource
import com.afurkantitiz.foodapp.data.remote.APIService
import com.afurkantitiz.foodapp.data.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        localDataSource: LocalDataSource
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val token = localDataSource.getToken()
                val request = it.request().newBuilder().addHeader("Authorization", token!!).build()
                it.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: APIService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://dist-learn.herokuapp.com/api/")
    }
}

data class EndPoint(val url: String)