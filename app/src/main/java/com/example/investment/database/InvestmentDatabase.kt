package com.example.investment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InvestmentData::class], version = 1, exportSchema = false)
abstract class InvestmentDatabase: RoomDatabase() {

    abstract fun investmentDao(): InvestmentDao

    companion object{
        @Volatile
        private var INSTANCE: InvestmentDatabase? = null

        fun getDatabase(context: Context): InvestmentDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InvestmentDatabase::class.java,
                    "investment_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}