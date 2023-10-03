package com.raywenderlich.nftmarketplace.datasource

import com.raywenderlich.nftmarketplace.model.NFT

interface NftDataSource {
    fun retrieveNFTs(): Collection<NFT>

    fun retrieveNFT(id: Int): NFT

    fun createNFT(nft: NFT): NFT

    fun updateNFT(nft: NFT): NFT

    fun deleteNFT(id: Int)
}