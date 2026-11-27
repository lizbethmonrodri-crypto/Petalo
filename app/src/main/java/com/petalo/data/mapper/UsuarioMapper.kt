
package com.petalo.data.mapper

import com.petalo.entity.UsuarioEntity
import com.petalo.domain.model.Usuario

fun UsuarioEntity.toDomain(): Usuario {
    return Usuario(
        id = id,
        nombre = nombre,
        correo = correo,
        rol = rol
    )
}

fun Usuario.toEntity(passwordHash: String): UsuarioEntity {
    return UsuarioEntity(
        id = id,
        nombre = nombre,
        correo = correo,
        passwordHash = passwordHash,
        rol = rol
    )
}
