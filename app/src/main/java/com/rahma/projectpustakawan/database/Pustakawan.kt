package com.rahma.projectpustakawan.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbPustakawan")
data class Pustakawan(
@PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "idPustakawan") val idPustakawan : Int,
@ColumnInfo(name = "namaPustakawan") val namaPustakawan : String,
@ColumnInfo(name = "statusPustakawan") val statusPustakawan : String,
@ColumnInfo(name = "tanggal_registrasi") val tanggal_registrasi : String
)


