package com.example.investment.database

import androidx.lifecycle.LiveData

class InvestmentRepository(private val investmentDao: InvestmentDao) {

    val getAllData: LiveData<List<InvestmentData>> = investmentDao.getAllData()

    suspend fun addInvestment(investment: InvestmentData){
        investmentDao.addInvestment(investment)
    }
}