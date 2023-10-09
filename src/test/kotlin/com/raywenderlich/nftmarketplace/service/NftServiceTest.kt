package com.raywenderlich.nftmarketplace.service

import com.raywenderlich.nftmarketplace.datasource.NftDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class NftServiceTest {

    private val dataSource: NftDataSource = mockk(relaxed = true)

    private val nftService = NftService(dataSource)

    @Test
    fun `should call its data source to retrieve NFTs`() {
        // when
        nftService.getNFTs()

        // then
        verify(exactly = 1) { dataSource.retrieveNFTs() }
    }
}