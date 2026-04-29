package com.hotel.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hotel.app.data.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: ReviewEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewEntity>)

    @Update
    suspend fun updateReview(review: ReviewEntity)

    @Delete
    suspend fun deleteReview(review: ReviewEntity)

    @Query("DELETE FROM reviews WHERE id = :reviewId")
    suspend fun deleteReviewById(reviewId: Int)

    @Query("SELECT * FROM reviews ORDER BY reviewDate DESC")
    fun getAllReviews(): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM reviews WHERE id = :reviewId")
    suspend fun getReviewById(reviewId: Int): ReviewEntity?

    @Query("SELECT * FROM reviews WHERE roomId = :roomId ORDER BY reviewDate DESC")
    fun getReviewsByRoom(roomId: Int): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM reviews WHERE guestId = :guestId ORDER BY reviewDate DESC")
    fun getReviewsByGuest(guestId: Int): Flow<List<ReviewEntity>>

    @Query("SELECT AVG(overallRating) FROM reviews WHERE roomId = :roomId")
    suspend fun getAverageRatingForRoom(roomId: Int): Float?

    @Query("SELECT COUNT(*) FROM reviews WHERE roomId = :roomId")
    fun getReviewsCountForRoom(roomId: Int): Flow<Int>

    @Query("SELECT * FROM reviews WHERE isVerified = 1 ORDER BY reviewDate DESC")
    fun getVerifiedReviews(): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM reviews WHERE isRecommended = 1 ORDER BY reviewDate DESC")
    fun getRecommendedReviews(): Flow<List<ReviewEntity>>

    @Query("""
        SELECT * FROM reviews 
        WHERE roomId = :roomId 
        AND overallRating >= :minRating 
        ORDER BY reviewDate DESC
    """)
    fun getReviewsByRoomAndRating(roomId: Int, minRating: Float): Flow<List<ReviewEntity>>
}
