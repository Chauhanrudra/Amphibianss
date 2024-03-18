package com.example.amphibians.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Amphibian(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    val imageUrl: String
)
