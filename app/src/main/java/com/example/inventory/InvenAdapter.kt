package com.example.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.ListItemBinding
import com.google.android.material.imageview.ShapeableImageView

class InvenAdapter(): RecyclerView.Adapter<InvenAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return prodList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = prodList[position]
//        holder.nameprod.setImageResource(currentItem.img_link)
        holder.nameprod.text = currentItem.name
        holder.availqty.text = currentItem.available_qty.toString()
        holder.orderqty.text = currentItem.ordered_qty.toString()
        holder.totalqty.text = currentItem.total_qty.toString()
    }

    class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){


        val nameprod : TextView = binding.namaprod
        val availqty : TextView = binding.availqty
        val orderqty : TextView = binding.orderqty
        val totalqty : TextView = binding.totalqty


    }

//    tambahan belum paham
    private val diffCallback = object : DiffUtil.ItemCallback<Items>(){
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var prodList: List<Items>
    get() = differ.currentList
    set(value) {differ.submitList(value)}
    //    tambahan belum paham
}