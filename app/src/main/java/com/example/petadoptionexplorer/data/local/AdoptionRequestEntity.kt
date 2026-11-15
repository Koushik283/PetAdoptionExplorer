
package com.example.petadoptionexplorer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adoption_requests")
data class AdoptionRequestEntity(
    @PrimaryKey(autoGenerate = true)
    val requestId: Int = 0,
    val dogId: Int,
    val dogName: String,
    val applicantName: String,
    val email: String,
    val phone: String,
    val address: String,
    val message: String,
    val submittedAt: Long = System.currentTimeMillis()
)
