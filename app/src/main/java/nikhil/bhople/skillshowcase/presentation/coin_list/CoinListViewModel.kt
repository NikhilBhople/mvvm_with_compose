package nikhil.bhople.skillshowcase.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nikhil.bhople.skillshowcase.common.Constants.EMPTY_STRING
import nikhil.bhople.skillshowcase.common.Resource
import nikhil.bhople.skillshowcase.domain.use_case.get_coins.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CoinState())
    val state: State<CoinState> = _state

    init {
        getCoinList()
    }

    fun getCoinList() {
        getCoinUseCase().onEach { result ->
            when (result) {
                is Resource.Failure -> _state.value =
                    CoinState(failure = result.message ?: EMPTY_STRING)
                is Resource.Loading -> _state.value = CoinState(isLoading = true)
                is Resource.Success -> _state.value = CoinState(data = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}