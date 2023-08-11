package com.claucas90.e3m6.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Palabras::class), version = 1, exportSchema = false)
public abstract class PalabrasRoomDatabase: RoomDatabase(){
    abstract fun PalabrasDao(): PalabrasDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PalabrasRoomDatabase? = null

        fun getDatabase(context: Context): PalabrasRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PalabrasRoomDatabase::class.java,
                    "palabras_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}