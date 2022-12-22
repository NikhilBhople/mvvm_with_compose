package nikhil.bhople.skillshowcase.presentation.coin_details

import nikhil.bhople.skillshowcase.common.Constants
import nikhil.bhople.skillshowcase.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val data: CoinDetails? = null,
    val error: String = Constants.EMPTY_STRING,
    val coinName: String = ""
)