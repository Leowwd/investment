package com.example.investment.adapter

import android.content.Context
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.investment.R
import com.example.investment.ThirdFragment
import com.example.investment.database.InvestmentData

class InvestmentAdapter(): RecyclerView.Adapter<InvestmentAdapter.InvestmentViewHolder>() {
    private var itemList = emptyList<InvestmentData>()
    class InvestmentViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val inputText: TextView = itemView.findViewById(R.id.textView6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestmentViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.db_item, parent, false)
        return InvestmentViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: InvestmentViewHolder, position: Int) {
        var item = itemList[position]
        if (item != null) {
            holder.inputText.text = "${item.id}  本金：" + "%.0f　".format(item.a0) + "報酬：%.0f　".format(item.expectedReturn) + "年數：%.0f　".format(item.year) + "IRR：%.2f%%".format(item.IRR*100)
            holder.itemView.setOnClickListener {
                sendSMS("1234567",
                    "此筆資料的本金為：${item.a0}\r\n" +
                            "報酬為：${item.expectedReturn}\r\n" +
                            "年數為：${item.year}\n\r" +
                            "IRR為：" + "IRR：%.2f%%".format(item.IRR*100))
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(data: List<InvestmentData>){
        this.itemList = data
        notifyDataSetChanged()
    }
    private fun sendSMS(phoneNumber: String, message: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }
}