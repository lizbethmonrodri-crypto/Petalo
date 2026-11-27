
package com.petalo.dao

import androidx.room.*
import com.petalo.entity.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuarios ORDER BY id DESC")
    fun obtenerTodos(): Flow<List<UsuarioEntity>>

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun buscarPorCorreo(correo: String): UsuarioEntity?

    @Update
    suspend fun actualizar(usuario: UsuarioEntity)

    @Delete
    suspend fun eliminar(usuario: UsuarioEntity)
}
