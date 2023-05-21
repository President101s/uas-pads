package com.example.inventory

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.FragmentInventoryBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Inventory"

class Inventory : Fragment() {

    private lateinit var binding: FragmentInventoryBinding
    private lateinit var newRecyclerview : RecyclerView
    private lateinit var newArrayList : ArrayList<All_prod>

    private lateinit var invenAdapter: InvenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInventoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchTodoData()

        binding.goToTransactionButton.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_inventory_to_transaction)
        }

        binding.sortButton.setOnClickListener {
            // Show a dialog or perform any desired action when the button is clicked
            showSortDialog()
        }

    }

    private fun showSortDialog() {
        val options = arrayOf("Available", "Total", "Ordered")

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Sort By")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> sortByAvailable()
                    1 -> sortByTotal()
                    2 -> sortByOrdered()
                }
            }
            .show()
    }

    private fun sortByAvailable() {
        val sortedList = invenAdapter.prodList.toMutableList().apply {
            sortByDescending { it.available_qty }
        }
        invenAdapter.prodList = sortedList
        invenAdapter.notifyDataSetChanged()
    }

    private fun sortByTotal() {
        val sortedList = invenAdapter.prodList.toMutableList().apply {
            sortByDescending { it.total_qty }
        }
        invenAdapter.prodList = sortedList
        invenAdapter.notifyDataSetChanged()
    }

    private fun sortByOrdered() {
        val sortedList = invenAdapter.prodList.toMutableList().apply {
            sortByDescending { it.ordered_qty }
        }
        invenAdapter.prodList = sortedList
        invenAdapter.notifyDataSetChanged()
    }



    private fun setupRecyclerView() = binding.recyclerView.apply {
        invenAdapter = InvenAdapter()
        adapter = invenAdapter
        layoutManager = LinearLayoutManager(context)
    }

    private fun fetchTodoData() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getInven()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have an internet connection", e)
                binding.progressBar.isVisible = false
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response", e)
                binding.progressBar.isVisible = false
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                invenAdapter.prodList = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }

            binding.progressBar.isVisible = false
        }
    }

}