package nikhil.bhople.skillshowcase.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nikhil.bhople.skillshowcase.common.Constants
import nikhil.bhople.skillshowcase.common.Resource
import nikhil.bhople.skillshowcase.domain.model.Coin
import nikhil.bhople.skillshowcase.domain.repository.CryptoRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getCoins()))
        } catch (e: HttpException) {
            emit(Resource.Failure(e.localizedMessage ?: Constants.HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Failure(Constants.INTERNET_CONNECTION_MSG))
        }
    }
}