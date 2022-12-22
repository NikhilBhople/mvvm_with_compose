package nikhil.bhople.skillshowcase.domain.model

import nikhil.bhople.skillshowcase.data.dto.CoinDetailsDto

data class CoinDetails(
    val description: String,
    val coinId: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<CoinDetailsDto.TeamMember>,
)