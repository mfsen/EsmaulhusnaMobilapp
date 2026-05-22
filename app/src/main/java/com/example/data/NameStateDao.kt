package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NameStateDao {
    @Query("SELECT * FROM name_states")
    fun getAllNameStates(): Flow<List<NameState>>

    @Query("SELECT * FROM name_states WHERE nameId = :nameId")
    fun getNameStateById(nameId: Int): Flow<NameState?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateState(state: NameState)

    @Query("SELECT * FROM name_states WHERE isFavorite = 1")
    fun getFavoriteStates(): Flow<List<NameState>>
}
