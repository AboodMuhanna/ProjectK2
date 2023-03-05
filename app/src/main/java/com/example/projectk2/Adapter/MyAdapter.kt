package com.example.projectk2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectk2.Data
import com.example.projectk2.R

class MyAdapter(private val dataList : ArrayList<Data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val data :  Data = dataList[position]
        holder.name.text = data.name
        holder.number.text = data.number
        holder.address.text = data.address
    }

    override fun getItemCount(): Int {
          return  dataList.size
    }

    public class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val name : TextView = itemView.findViewById(R.id.tvName)
        val number : TextView = itemView.findViewById(R.id.tvNumber)
            val address : TextView = itemView.findViewById(R.id.tvAddress)

    }
}