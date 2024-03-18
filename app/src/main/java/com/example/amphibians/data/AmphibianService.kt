package com.example.amphibians.data

import retrofit2.http.GET

interface AmphibianService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}
