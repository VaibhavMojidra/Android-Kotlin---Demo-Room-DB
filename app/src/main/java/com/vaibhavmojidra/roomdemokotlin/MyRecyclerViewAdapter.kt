package com.vaibhavmojidra.roomdemokotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vaibhavmojidra.roomdemokotlin.database.tables.Subscriber
import com.vaibhavmojidra.roomdemokotlin.databinding.ListItemBinding

class MyRecyclerViewAdapter(private var subscriberList:List<Subscriber>,private val clicklistener:(Subscriber)->Unit):RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=DataBindingUtil.inflate<ListItemBinding>(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position],clicklistener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }

    class MyViewHolder(private val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(subscriber:Subscriber,listener:(Subscriber)->Unit){
            binding.nameTextView.text=subscriber.name
            binding.emailTextView.text=subscriber.email
            binding.listLayout.setOnClickListener{
                listener(subscriber)
            }
        }
    }
}