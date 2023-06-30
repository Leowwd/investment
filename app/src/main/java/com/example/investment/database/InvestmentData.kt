package com.example.investment.database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "investment_table")
data class InvestmentData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "a0") val a0: Double,
    @ColumnInfo(name = "expectedReturn") val expectedReturn: Double,
    @ColumnInfo(name = "year") val year: Double,
    @ColumnInfo(name = "IRR") val IRR: Double,
)
