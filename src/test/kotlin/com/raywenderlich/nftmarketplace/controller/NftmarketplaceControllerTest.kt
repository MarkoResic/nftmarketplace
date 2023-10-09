package com.raywenderlich.nftmarketplace.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.raywenderlich.nftmarketplace.model.NFT
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class NftmarketplaceControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api"

    @Nested
    @DisplayName("GET /api")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetNFTs {

        @Test
        fun `should return all NFTs`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") { value("1") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetNFT {

        @Test
        fun `should return the NFT with the given id`() {
            // given
            val id = 1

            // when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name") { value("CryptoPunks") }
                    jsonPath("$.floorPrice") { value("100.0") }
                }
        }

        @Test
        fun `should return NOT FOUND if the id does not exist`() {
            // given
            val id = 999

            // when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewNFT {

        @Test
        fun `should add the new NFT`() {
            // given
            val newNFT = NFT(20, "Test NFT", 420.69)

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newNFT)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newNFT))
                    }
                }

            mockMvc.get("$baseUrl/${newNFT.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(newNFT)) } }
        }

        @Test
        fun `should return BAD REQUEST if NFT with given id already exists`() {
            // given
            val invalidNFT = NFT(1, "New NFT", 1.0)

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidNFT)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }

    @Nested
    @DisplayName("PATCH /api")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingNFT {

        @Test
        fun `should update an existing NFT`() {
            // given
            val updatedNFT = NFT(1, "New NFT", 1.0)

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedNFT)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedNFT))
                    }
                }

            mockMvc.get("$baseUrl/${updatedNFT.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedNFT)) } }
        }

        @Test
        fun `should return BAD REQUEST if no NFT with given id exists`() {
            // given
            val invalidNFT = NFT(999, "New NFT", 1.0)

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidNFT)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("DELETE /api/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingNFT {

        @Test
        @DirtiesContext
        fun `should delete the NFT with the given id`() {
            // given
            val id = 1

            // when/then
            mockMvc.delete("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$id")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT FOUND if no NFT with given id exists`() {
            // given
            val invalidId = 999

            // when/then
            mockMvc.delete("$baseUrl/$invalidId")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}