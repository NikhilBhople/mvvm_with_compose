package nikhil.bhople.skillshowcase.presentation.coin_list

import nikhil.bhople.skillshowcase.common.Constants
import nikhil.bhople.skillshowcase.domain.model.Coin

data class CoinState(
    val data: List<Coin> = emptyList(),
    val isLoading: Boolean = false,
    val failure: String = Constants.EMPTY_STRING
)