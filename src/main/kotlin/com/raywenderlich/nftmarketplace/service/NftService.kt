package com.raywenderlich.nftmarketplace.service

import com.raywenderlich.nftmarketplace.datasource.NftDataSource
import com.raywenderlich.nftmarketplace.model.NFT
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class NftService(@Qualifier("mock") private val dataSource: NftDataSource) {
    fun getNFTs(): Collection<NFT> = dataSource.retrieveNFTs()

    fun getNFT(id: Int): NFT = dataSource.retrieveNFT(id)

    fun addNFT(nft: NFT): NFT = dataSource.createNFT(nft)

    fun updateNFT(nft: NFT): NFT = dataSource.updateNFT(nft)

    fun deleteNFT(id: Int) = dataSource.deleteNFT(id)
}