package com.hotel.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hotel.app.data.util.DateConverter
import java.util.*

enum class RoomType {
    STANDARD,
    SEMI_LUXE,
    LUXE
}

enum class CleaningStatus {
    CLEAN,
    NEEDS_CLEANING,
    IN_PROGRESS
}

@Entity(tableName = "rooms")
@TypeConverters(DateConverter::class)
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val roomNumber: String,
    val roomType: RoomType,
    val floor: Int,
    val capacity: Int,
    val price: Double,
    val hasWiFi: Boolean = true,
    val hasAC: Boolean = true,
    val hasTV: Boolean = true,
    val hasMinibar: Boolean = false,
    val hasJacuzzi: Boolean = false,
    val hasBalcony: Boolean = false,
    val hasKitchen: Boolean = false,
    val hasSeatingArea: Boolean = false,
    val hasWorkDesk: Boolean = false,
    val cleaningStatus: CleaningStatus = CleaningStatus.CLEAN,
    val isAvailable: Boolean = true,
    val description: String = "",
    
    // For SEMI_LUXE and LUXE rooms
    val minCheckoutDays: Int = 0,
    val maxCheckoutDays: Int = 365,
    
    // Additional fields
    val lastCleaned: Date? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) {
    fun getAmenitiesCount(): Int {
        var count = 0
        if (hasWiFi) count++
        if (hasAC) count++
        if (hasTV) count++
        if (hasMinibar) count++
        if (hasJacuzzi) count++
        if (hasBalcony) count++
        if (hasKitchen) count++
        if (hasSeatingArea) count++
        if (hasWorkDesk) count++
        return count
    }

    fun getAmenitiesList(): List<String> {
        val amenities = mutableListOf<String>()
        if (hasWiFi) amenities.add("WiFi")
        if (hasAC) amenities.add("Air Conditioning")
        if (hasTV) amenities.add("TV")
        if (hasMinibar) amenities.add("Minibar")
        if (hasJacuzzi) amenities.add("Jacuzzi")
        if (hasBalcony) amenities.add("Balcony")
        if (hasKitchen) amenities.add("Kitchen")
        if (hasSeatingArea) amenities.add("Seating Area")
        if (hasWorkDesk) amenities.add("Work Desk")
        return amenities
    }
}
