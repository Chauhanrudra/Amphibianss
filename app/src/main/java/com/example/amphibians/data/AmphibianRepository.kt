package com.example.amphibians.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit

class AmphibianRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory("application/json".toMediaTypeOrNull()
            ?.let { Json { ignoreUnknownKeys = true }.asConverterFactory(it) })
        .build()

    private val service = retrofit.create(AmphibianService::class.java)

    suspend fun getAmphibians(): List<Amphibian> {
        return service.getAmphibians()
    }
}
