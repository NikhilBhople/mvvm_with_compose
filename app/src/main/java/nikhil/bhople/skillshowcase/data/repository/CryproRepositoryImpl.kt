package nikhil.bhople.skillshowcase.data.repository

import nikhil.bhople.skillshowcase.data.dto.toCoin
import nikhil.bhople.skillshowcase.data.dto.toCoinDetails
import nikhil.bhople.skillshowcase.data.remote.RetrofitCryptoNetworkApi
import nikhil.bhople.skillshowcase.domain.model.Coin
import nikhil.bhople.skillshowcase.domain.model.CoinDetails
import nikhil.bhople.skillshowcase.domain.repository.CryptoRepository
import javax.inject.Inject

class CryproRepositoryImpl @Inject constructor(
    private val networkDataSource: RetrofitCryptoNetworkApi
) : CryptoRepository {

    override suspend fun getCoinDetails(coinName: String): CoinDetails {
        return networkDataSource.getCoinDetails(coinName).toCoinDetails()
    }

    override suspend fun getCoins(): List<Coin> {
        return networkDataSource.getCoins().map { it.toCoin() }
    }
}