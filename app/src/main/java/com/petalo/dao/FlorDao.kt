
package com.petalo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.petalo.entity.FlorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlorDao {

    @Insert
    suspend fun insertar(flor: FlorEntity)

    @Query("SELECT * FROM flores ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<FlorEntity>>

    @Update
    suspend fun actualizar(flor: FlorEntity)

    @Delete
    suspend fun eliminar(flor: FlorEntity)
}
