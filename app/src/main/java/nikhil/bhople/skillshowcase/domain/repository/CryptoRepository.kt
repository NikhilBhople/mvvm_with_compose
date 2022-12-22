package nikhil.bhople.skillshowcase.domain.repository

import nikhil.bhople.skillshowcase.domain.model.Coin
import nikhil.bhople.skillshowcase.domain.model.CoinDetails

interface CryptoRepository {
    suspend fun getCoins(): List<Coin>
    suspend fun getCoinDetails(coinName: String): CoinDetails
}