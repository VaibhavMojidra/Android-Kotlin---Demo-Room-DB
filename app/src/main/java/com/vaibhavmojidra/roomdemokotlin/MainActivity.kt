package com.vaibhavmojidra.roomdemokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaibhavmojidra.roomdemokotlin.database.dao.SubscriberDAO
import com.vaibhavmojidra.roomdemokotlin.database.db.SubscriberDatabase
import com.vaibhavmojidra.roomdemokotlin.database.repositories.SubscriberRepository
import com.vaibhavmojidra.roomdemokotlin.database.tables.Subscriber
import com.vaibhavmojidra.roomdemokotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var viewModelFactory: SubscriberViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao=SubscriberDatabase.getInstance(applicationContext).subscriberDAO
        val repository=SubscriberRepository(dao)
        viewModelFactory= SubscriberViewModelFactory(repository)
        viewModel=ViewModelProvider(this,viewModelFactory).get(SubscriberViewModel::class.java)
        binding.myViewModel=viewModel
        binding.lifecycleOwner=this
        initRecyclerView()
        viewModel.message.observe(this,{
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        displaySubscribesList()
    }

    private fun displaySubscribesList(){
        viewModel.subscribers.observe(this,{
            binding.recyclerView.adapter=MyRecyclerViewAdapter(it,{subscriber:Subscriber->clickItem(subscriber)})
        })
    }
    private fun clickItem(subscriber: Subscriber){
        viewModel.initDeleteAndUpdate(subscriber)
    }
}