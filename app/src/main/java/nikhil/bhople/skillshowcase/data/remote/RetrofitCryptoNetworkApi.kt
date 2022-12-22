package nikhil.bhople.skillshowcase.data.remote

import nikhil.bhople.skillshowcase.data.dto.CoinDetailsDto
import nikhil.bhople.skillshowcase.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitCryptoNetworkApi {
    @GET(value = "/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET(value = "/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): CoinDetailsDto
}