package com.example.easynote.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easynote.Models.Note
import com.example.easynote.Utilities.DATABASE_NAME


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getDao() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context : Context) : NoteDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }


    }
}