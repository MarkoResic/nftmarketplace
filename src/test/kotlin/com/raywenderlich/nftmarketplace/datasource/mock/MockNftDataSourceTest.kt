package com.raywenderlich.nftmarketplace.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MockNftDataSourceTest {

    private val mockDataSource = MockNftDataSource()

    @Test
    fun `should provide some mock data`() {
        // when
        val nfts = mockDataSource.retrieveNFTs()

        // then
        assertThat(nfts).allMatch { it.id != 0 }
        assertThat(nfts).anyMatch { it.name.isNotBlank() }
        assertThat(nfts).anyMatch { it.floorPrice != 0.0 }
    }
}