package com.rahma.projectpustakawan.database

import androidx.room.*

@Dao
interface pustakawanDAO {
    @Query ("SELECT * FROM tbPustakawan")
    fun getAll() : List<Pustakawan>
    @Insert
    fun insertData (pustakawan: Pustakawan)
    @Update
    fun updateData (pustakawan: Pustakawan)
    @Delete
    fun deleteData (pustakawan: Pustakawan)
}