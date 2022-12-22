package nikhil.bhople.skillshowcase.presentation.coin_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nikhil.bhople.skillshowcase.common.Constants
import nikhil.bhople.skillshowcase.common.Resource
import nikhil.bhople.skillshowcase.domain.use_case.get_coin_details.GetCoinDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val coinDetailState = _state

    init {
        savedStateHandle.get<String>(Constants.PARAMS_COIN_NAME)?.let { coinName ->
            _state.value = CoinDetailState().copy(coinName = coinName)
            getCoinDetails()
        }
    }

    fun getCoinDetails() {
        getCoinDetailsUseCase(_state.value.coinName).onEach { result ->
            when (result) {
                is Resource.Failure -> _state.value = _state.value.copy(
                    error = result.message ?: Constants.HTTP_ERROR_MSG, isLoading = false
                )
                is Resource.Loading -> _state.value =
                    _state.value.copy(isLoading = true, error = Constants.EMPTY_STRING)
                is Resource.Success -> result.data?.let {
                    _state.value = _state.value.copy(data = it, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}