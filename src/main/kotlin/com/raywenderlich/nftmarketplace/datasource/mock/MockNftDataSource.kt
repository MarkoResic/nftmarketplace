package com.raywenderlich.nftmarketplace.datasource.mock

import com.raywenderlich.nftmarketplace.datasource.NftDataSource
import com.raywenderlich.nftmarketplace.model.NFT
import org.springframework.stereotype.Repository

@Repository("mock")
class MockNftDataSource : NftDataSource {

    private var nfts = mutableListOf(
            NFT(1, "CryptoPunks", 100.0),
            NFT(2, "Sneaky Vampire Syndicate", 36.9),
            NFT(3, "The Sevens (Official)", 0.6),
            NFT(4, "Art Blocks Curated", 1.1),
            NFT(5, "Pudgy Penguins", 2.5),
    )

    override fun retrieveNFTs(): Collection<NFT> = nfts

    override fun retrieveNFT(id: Int): NFT =
        nfts.firstOrNull() { it.id == id }
                ?: throw NoSuchElementException("Could not find an NFT with id $id")

    override fun createNFT(nft: NFT): NFT {
        if (nfts.any { it.id == nft.id }) {
            throw IllegalArgumentException("NFT with id ${nft.id} already exists.")
        }
        nfts.add(nft)

        return nft
    }

    override fun updateNFT(nft: NFT): NFT {
        val currentNft = nfts.firstOrNull { it.id == nft.id }
                ?: throw NoSuchElementException("Could not find an NFT with id ${nft.id}")

        nfts.remove(currentNft)
        nfts.add(nft)

        return nft
    }

    override fun deleteNFT(id: Int) {
        val currentNft = nfts.firstOrNull { it.id == id }
                ?: throw NoSuchElementException("Could not find an NFT with id $id")

        nfts.remove(currentNft)
    }
}