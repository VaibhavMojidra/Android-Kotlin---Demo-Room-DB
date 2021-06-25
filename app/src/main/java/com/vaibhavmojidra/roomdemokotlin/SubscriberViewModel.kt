package com.vaibhavmojidra.roomdemokotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhavmojidra.roomdemokotlin.database.repositories.SubscriberRepository
import com.vaibhavmojidra.roomdemokotlin.database.tables.Subscriber
import kotlinx.coroutines.launch

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel() {
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveAndUpdateBtnText = MutableLiveData<String>()
    val clearAllAndDeleteBtnText = MutableLiveData<String>()
    val subscribers = subscriberRepository.subscribers
    val message=MutableLiveData<String>()
    private var isUpdateOrDelete=false
    private lateinit var subscriberToUpdateAndDelete: Subscriber
    init {
        saveAndUpdateBtnText.value = "Save"
        clearAllAndDeleteBtnText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if(isUpdateOrDelete){
            subscriberToUpdateAndDelete.name= inputName.value!!
            subscriberToUpdateAndDelete.email=inputEmail.value!!
            update(subscriberToUpdateAndDelete)
        }
        else{
            val name=inputName.value!!
            val email=inputEmail.value!!
            insert(Subscriber(0,name,email))
            inputName.value=""
            inputEmail.value=""
        }
    }

    fun clearAllOrDelete() {
        if(isUpdateOrDelete){
            delete(subscriberToUpdateAndDelete)
        }
        else{
            clearAll()
        }

    }
    fun initDeleteAndUpdate(subscriber: Subscriber){
        inputName.value=subscriber.name
        inputEmail.value=subscriber.email
        isUpdateOrDelete=true
        subscriberToUpdateAndDelete=subscriber
        saveAndUpdateBtnText.value="Update"
        clearAllAndDeleteBtnText.value="Delete"
    }

    fun insert(subscriber: Subscriber) = viewModelScope.launch {
        val numberOfRowsInserted:Long=subscriberRepository.insert(subscriber)
        if(numberOfRowsInserted>0){
            "$numberOfRowsInserted rows inserted".also { message.value = it }
        }else{
            message.value="Error occurred"
        }
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.update(subscriber)
        inputName.value=""
        inputEmail.value=""
        isUpdateOrDelete=false
        saveAndUpdateBtnText.value="Save"
        clearAllAndDeleteBtnText.value="Clear All"
        message.value="Updated"
    }
    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        subscriberRepository.delete(subscriber)
        inputName.value=""
        inputEmail.value=""
        isUpdateOrDelete=false
        saveAndUpdateBtnText.value="Save"
        clearAllAndDeleteBtnText.value="Clear All"
        message.value="Deleted"
    }

    fun clearAll() = viewModelScope.launch {
        subscriberRepository.deleteAll()
    }
}