
package com.petalo.data.di

import android.content.Context
import com.petalo.database.AppDatabase
import com.petalo.data.repository.PetaloRepositoryImpl
import com.petalo.domain.repository.PetaloRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun proporcionarBaseDatos(
        @ApplicationContext context: Context
    ): AppDatabase {

        return AppDatabase.obtenerBaseDatos(context)
    }

    @Provides
    @Singleton
    fun proporcionarRepository(
        database: AppDatabase
    ): PetaloRepository {

        return PetaloRepositoryImpl(
            florDao = database.florDao(),
            pedidoDao = database.pedidoDao(),
            usuarioDao = database.usuarioDao(),
            carritoDao = database.carritoDao()
        )
    }
}
