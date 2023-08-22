package com.rahma.projectpustakawan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pustakawan::class], version = 1)
abstract class DBPustakawan : RoomDatabase() {
    abstract fun pustakawanDao() : pustakawanDAO

    companion object{
        @Volatile
        private var instance : DBPustakawan? =null
        private var key = Any()

        @Synchronized
        fun getInstance (context: Context) : DBPustakawan{
            if (instance==null){
                instance = Room.databaseBuilder(context,DBPustakawan::class.java,"PUSTAKAWAN")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}