package com.raywenderlich.nftmarketplace.controller

import com.raywenderlich.nftmarketplace.model.NFT
import com.raywenderlich.nftmarketplace.service.NftService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class NftmarketplaceController(private val service: NftService) {

    @Value("\${company_name}")
    private lateinit var name: String

    @GetMapping("/homepage")
    fun getHomePage() = "$name: NFTs Marketplace"

    @GetMapping("/api")
    fun getNFTs(): Collection<NFT> = service.getNFTs()

    @GetMapping("/api/{id}")
    fun getNFT(@PathVariable id: Int): NFT = service.getNFT(id)

    @PostMapping("/api")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNFT(@RequestBody nft: NFT): NFT = service.addNFT(nft)

    @PatchMapping("/api")
    fun updateNFT(@RequestBody nft: NFT): NFT = service.updateNFT(nft)

    @DeleteMapping("/api/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNFT(@PathVariable id: Int) = service.deleteNFT(id)
}