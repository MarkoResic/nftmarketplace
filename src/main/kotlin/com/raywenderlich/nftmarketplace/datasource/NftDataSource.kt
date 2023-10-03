package com.raywenderlich.nftmarketplace.datasource

import com.raywenderlich.nftmarketplace.model.NFT

interface NftDataSource {
    fun retrieveBanks(): Collection<NFT>

    fun retrieveBank(accountNumber: String): NFT

    fun createBank(bank: NFT): NFT

    fun updateBank(bank: NFT): NFT

    fun deleteBank(accountNumber: String)
}