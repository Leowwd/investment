package com.example.investment.model

import android.app.Application
import android.provider.Telephony.Mms.Rate
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.investment.database.InvestmentData
import com.example.investment.database.InvestmentDatabase
import com.example.investment.database.InvestmentRepository
import com.example.investment.network.RateApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.math.pow

class IrrViewModel(application: Application): AndroidViewModel(application) {
    private var _a0 = 0.0
    val a0: Double
        get() = _a0

    private var _eReturn = 0.0
    val eReturn: Double
        get() = _eReturn

    private var _year = 0.0
    val year: Double
        get() = _year

    private var _IRR = 0.0
    val IRR: Double
        get() = _IRR

    var rate: Double = 0.0

    val getAllData: LiveData<List<InvestmentData>>
    private val repository: InvestmentRepository

    fun getIRR(a0: String, expectedReturn: String, year: String){
        _a0 = a0.toDouble()
        _eReturn = expectedReturn.toDouble()
        _year = year.toDouble()
        _IRR = (_eReturn / _a0).pow(1/_year) - 1
    }

    fun IsValuable(): String{
        var result = ""
        if (IRR > rate) {
            result = "計算出的內部報酬率為:\n\r" + "%.2f".format(_IRR*100) + "%\n\r值得投資"
        } else {
            result = "計算出的內部報酬率為:\n\r" + "%.2f".format(_IRR*100) + "%\n\r不值得投資"
        }
        return result
    }

    init {
        _a0 = 0.0
        _eReturn = 0.0
        _year = 0.0
        val investmentDao = InvestmentDatabase.getDatabase(application).investmentDao()
        repository = InvestmentRepository(investmentDao)
        getAllData = repository.getAllData
    }

    fun addInvestment(investment: InvestmentData){
        viewModelScope.launch(Dispatchers.IO){
            repository.addInvestment(investment)
        }
    }
}