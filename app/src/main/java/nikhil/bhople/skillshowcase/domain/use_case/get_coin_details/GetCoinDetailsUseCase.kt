package nikhil.bhople.skillshowcase.domain.use_case.get_coin_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nikhil.bhople.skillshowcase.common.Constants
import nikhil.bhople.skillshowcase.common.Resource
import nikhil.bhople.skillshowcase.domain.model.CoinDetails
import nikhil.bhople.skillshowcase.domain.repository.CryptoRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    operator fun invoke(coinName: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getCoinDetails(coinName)))
        } catch (ex: HttpException) {
            emit(Resource.Failure(ex.localizedMessage ?: Constants.HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Failure(Constants.INTERNET_CONNECTION_MSG))
        }
    }
}