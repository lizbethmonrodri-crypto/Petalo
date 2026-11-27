
package com.petalo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.petalo.entity.PedidoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Insert
    suspend fun insertar(pedido: PedidoEntity)

    @Query("SELECT * FROM pedidos ORDER BY id DESC")
    fun obtenerTodos(): Flow<List<PedidoEntity>>

    @Query("SELECT * FROM pedidos WHERE florId = :idFlor")
    fun obtenerPorFlor(idFlor: Int): Flow<List<PedidoEntity>>

    @Update
    suspend fun actualizar(pedido: PedidoEntity)

    @Delete
    suspend fun eliminar(pedido: PedidoEntity)
}
