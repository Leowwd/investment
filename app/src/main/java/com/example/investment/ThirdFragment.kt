package com.example.investment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investment.adapter.InvestmentAdapter
import com.example.investment.databinding.FragmentThirdBinding
import com.example.investment.model.IrrViewModel

class ThirdFragment: Fragment() {
    private lateinit var binding: FragmentThirdBinding
    private val viewModel: IrrViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = InvestmentAdapter()
        val resultRecyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        resultRecyclerView.layoutManager = LinearLayoutManager(context)
        resultRecyclerView.adapter = adapter
        viewModel.getAllData.observe(viewLifecycleOwner, Observer { item ->
            adapter.setData(item)
        })
    }
}