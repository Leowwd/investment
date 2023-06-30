package com.example.investment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InvestmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInvestment(investment: InvestmentData)


    @Query("SELECT * FROM investment_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<InvestmentData>>
}