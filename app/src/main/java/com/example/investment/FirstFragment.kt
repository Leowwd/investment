package com.example.investment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.investment.database.InvestmentData
import com.example.investment.databinding.FragmentFirstBinding
import com.example.investment.model.IrrViewModel
import com.example.investment.network.RateApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: IrrViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch{
            try {
                val rateApi = RateApi.retrofitService.getRate()
                Log.d("連線結果", "rate = ${rateApi[4].thirteen.toDouble()/1000}")
                viewModel.rate = rateApi[4].thirteen.toDouble()/100000
                Log.d("連線結果", "rate = ${viewModel.rate}")
            } catch (e: Exception) {
                viewModel.rate = 0.011
                Log.d("連線結果","連線失敗！API又在搞")
            }
        }

        binding.button2.setOnClickListener {
            var aInput = binding.inputEdit1.text.toString()
            var rInput = binding.inputEdit2.text.toString()
            var yInput = binding.inputEdit3.text.toString()
            if(inputCheck(aInput, rInput, yInput)){
                viewModel.getIRR(aInput, rInput, yInput)
                viewModel.addInvestment(InvestmentData(0, viewModel.a0, viewModel.eReturn, viewModel.year, viewModel.IRR))
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }else{
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
            }
            Log.d("Tag", "IRR = ${viewModel.IRR}")
            binding.inputEdit1.text = null
            binding.inputEdit2.text = null
            binding.inputEdit3.text = null
        }

    }

    private fun inputCheck(aInput: String, rInput: String, yInput: String): Boolean {
        return !(TextUtils.isEmpty(aInput) || TextUtils.isEmpty(rInput) || TextUtils.isEmpty(yInput))
    }

}
