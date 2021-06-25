package com.vaibhavmojidra.roomdemokotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaibhavmojidra.roomdemokotlin.database.repositories.SubscriberRepository
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val subscriberRepository: SubscriberRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(subscriberRepository) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }

}