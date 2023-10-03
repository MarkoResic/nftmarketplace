package com.raywenderlich.nftmarketplace.controller

import com.raywenderlich.nftmarketplace.model.NFT
import com.raywenderlich.nftmarketplace.service.NftService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class NftmarketplaceController(private val service: NftService) {

    @GetMapping("/homepage")
    fun getHomePage() = "NFTs Marketplace"

    @GetMapping("/api")
    fun getNFTs(): Collection<NFT> = service.getNFTs()

    @GetMapping("/api/{id}")
    fun getNFT(@PathVariable id: Int): NFT = service.getNFT(id)

    @PostMapping("/api")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNFT(@RequestBody nft: NFT): NFT {
        val maxId = service.getNFTs().maxOfOrNull { it.id } ?: 0
        val newNft = NFT(id = maxId + 1, name = nft.name, floorPrice = nft.floorPrice)

        service.addNFT(newNft)
        return newNft
    }

    @PatchMapping("/api")
    fun updateNFT(@RequestBody nft: NFT): NFT = service.updateNFT(nft)

    @DeleteMapping("/api/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNFT(@PathVariable id: Int) = service.deleteNFT(id)
}