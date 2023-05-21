package com.example.inventory

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventory.databinding.FragmentTransactionBinding
import kotlinx.coroutines.launch


class Transaction : Fragment() {


    private lateinit var binding: FragmentTransactionBinding
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchOrder()
    }


    private fun setupRecyclerView() {
        orderAdapter = OrderAdapter()
        binding.recyclerView.adapter = orderAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        orderAdapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                showConfirmationDialog(position)
            }

        })


        orderAdapter.setOnButtonClickListener(object : OrderAdapter.onButtonClickListener{
            override fun onButtonClick(position: Int) {
                showConfirmationDialog(position)
            }

        })
    }

    private fun fetchOrder(){
        viewLifecycleOwner.lifecycleScope.launch {
            val response = RetrofitInstance.api.getOrder()

            if (response.body() != null){
                orderAdapter.orderList = response.body()!!
            }
        }
    }

    private fun showConfirmationDialog(position: Int) {

        if (orderAdapter.orderList[position].is_promo == true){
            val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Confirmation")
            .setMessage("Are you sure you want to change the status to 'canceled'?")
            .setPositiveButton("Yes") { dialog, _ ->
                changeOrderStatus(position)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

            alertDialog.show()}



    }

    private fun changeOrderStatus(position: Int) {
        val order = orderAdapter.orderList[position]
        order.is_promo = false

        // Update the order in the adapter
        orderAdapter.notifyItemChanged(position)
    }

}