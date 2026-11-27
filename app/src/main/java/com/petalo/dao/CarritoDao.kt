
package com.petalo.dao

import androidx.room.*
import com.petalo.entity.CarritoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {

    @Insert
    suspend fun insertar(carrito: CarritoEntity)

    @Query("SELECT * FROM carrito")
    fun obtenerTodos(): Flow<List<CarritoEntity>>

    @Query("SELECT * FROM carrito WHERE usuarioId = :usuarioId")
    fun obtenerPorUsuario(
        usuarioId: Int
    ): Flow<List<CarritoEntity>>

    @Update
    suspend fun actualizar(carrito: CarritoEntity)

    @Delete
    suspend fun eliminar(carrito: CarritoEntity)
}
