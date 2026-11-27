
package com.petalo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.petalo.dao.*
import com.petalo.entity.*

@Database(
    entities = [
        FlorEntity::class,
        PedidoEntity::class,
        UsuarioEntity::class,
        CarritoEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun florDao(): FlorDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun carritoDao(): CarritoDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(db: SupportSQLiteDatabase) {

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS usuarios (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        nombre TEXT NOT NULL,
                        correo TEXT NOT NULL,
                        passwordHash TEXT NOT NULL,
                        rol TEXT NOT NULL
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_usuarios_correo ON usuarios(correo)"
                )

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS carrito (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        usuarioId INTEGER NOT NULL,
                        florId INTEGER NOT NULL,
                        cantidad INTEGER NOT NULL,
                        FOREIGN KEY(usuarioId) REFERENCES usuarios(id)
                            ON DELETE CASCADE,
                        FOREIGN KEY(florId) REFERENCES flores(id)
                            ON DELETE CASCADE
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    "CREATE INDEX IF NOT EXISTS index_carrito_usuarioId ON carrito(usuarioId)"
                )

                db.execSQL(
                    "CREATE INDEX IF NOT EXISTS index_carrito_florId ON carrito(florId)"
                )

                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_carrito_usuarioId_florId ON carrito(usuarioId, florId)"
                )
            }
        }

        fun obtenerBaseDatos(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "petalo_db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
