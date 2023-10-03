package com.raywenderlich.nftmarketplace.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NftmarketplaceController {
    @GetMapping("/homepage")
    fun getHomePage() = "NFTs Marketplace"
}