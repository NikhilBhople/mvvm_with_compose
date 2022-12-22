package nikhil.bhople.skillshowcase.presentation.coin_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nikhil.bhople.skillshowcase.domain.model.CoinDetails
import nikhil.bhople.skillshowcase.presentation.coin_details.CoinDetailsViewModel

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.coinDetailState.value

    Box(modifier = Modifier.fillMaxSize()) {

        state.data?.let { coinDetails ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    BuildHeaderRow(coinDetails)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = coinDetails.description,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BuildTagGrid(coinDetails)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(coinDetails.team) { team ->
                    TeamListItem(
                        team = team, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Divider()
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center)
                    .clickable { viewModel.getCoinDetails() }
            )
        }
    }
}

@Composable
private fun BuildTagGrid(coinDetails: CoinDetails) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .requiredHeight(88.dp)
            .fillMaxWidth()
    ) {
        items(coinDetails.tags) { tag ->
            CoinTag(tag = tag)
        }
    }
}

@Composable
private fun BuildHeaderRow(coinDetails: CoinDetails) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${coinDetails.rank}. ${coinDetails.name} ${coinDetails.symbol}",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(8f)
        )
        Text(
            text = if (coinDetails.isActive) "Active" else "Inactive",
            color = if (coinDetails.isActive) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(CenterVertically)
                .weight(4f),
        )
    }
}