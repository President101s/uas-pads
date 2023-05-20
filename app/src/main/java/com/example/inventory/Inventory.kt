package com.example.inventory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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
    private lateinit var newArrayList : ArrayList<Items>

    private lateinit var invenAdapter: InvenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInventoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

//        viewLifecycleOwner.lifecycleScope.launch{
//            binding.progressBar.isVisible = true
//            val response = try {
//                RetrofitInstance.api.getInven()
//            } catch (e: IOException){
//                Log.e(TAG, "IOException, you might not have internet connection")
//                binding.progressBar.isVisible = false
//                return@launch
//            } catch (e: HttpException) {
//                Log.e(TAG, "HttpException, unexpected response")
//                binding.progressBar.isVisible = false
//                return@launch
//            }
//            if(response.isSuccessful && response.body() != null){
//                invenAdapter.prodList = response.body()!!
//            } else{
//                Log.e(TAG, "Response not successful")
//            }
//            binding.progressBar.isVisible = false
//        }

//        setupRecyclerView()
        return binding.root

//        newRecyclerview = binding.recyclerView
//        newRecyclerview.layoutManager = LinearLayoutManager(context)
//        newRecyclerview.setHasFixedSize(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchTodoData()
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