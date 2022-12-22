package nikhil.bhople.skillshowcase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nikhil.bhople.skillshowcase.data.remote.RetrofitCryptoNetworkApi
import nikhil.bhople.skillshowcase.data.repository.CryproRepositoryImpl
import nikhil.bhople.skillshowcase.domain.repository.CryptoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.coinpaprika.com"

    @Provides
    @Singleton
    fun provideRetrofitCryptoNetworkApi(): RetrofitCryptoNetworkApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RetrofitCryptoNetworkApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(api: RetrofitCryptoNetworkApi): CryptoRepository {
        return CryproRepositoryImpl(api)
    }

}