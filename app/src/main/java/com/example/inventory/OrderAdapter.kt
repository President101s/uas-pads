package com.example.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.OrderItemBinding

class OrderAdapter(): RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {


    //    on click listener for each item
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }
    //    on click listener for each item


    private val diffCallback = object : DiffUtil.ItemCallback<OrderItem>(){
        override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var orderList: List<OrderItem>
    get() = differ.currentList
        set(value) {differ.submitList(value)}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }


    class MyViewHolder(private val binding: OrderItemBinding, listener: onItemClickListener): RecyclerView.ViewHolder(binding.root) {

        val thedate: TextView = binding.dateTextView
        val cusname: TextView = binding.customerNameTextView
        val totalqty: TextView = binding.totalQuantityTextView
        val totalprice: TextView = binding.totalPriceTextView
        val orderstat: TextView = binding.statusTextView
        val statchange: Button = binding.settingsButton

        //    on click listener for each item
        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        //    on click listener for each item




    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = orderList[position]

        holder.thedate.text = currentItem.id.toString()
        holder.cusname.text = currentItem.name
        holder.totalqty.text = currentItem.total_qty.toString()
        holder.totalprice.text = currentItem.price.toString()
        holder.orderstat.text = currentItem.is_promo.toString()

    }
}