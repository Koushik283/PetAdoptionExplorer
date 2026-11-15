
package com.example.petadoptionexplorer.model

data class AdoptionRequest(
    val requestId: Long = 0,
    val dogId: Int,
    val dogName: String,
    val applicantName: String,
    val email: String,
    val phone: String,
    val address: String,
    val message: String = "",
    val submittedAt: Long = System.currentTimeMillis()
)
