package com.vaibhavmojidra.roomdemokotlin.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vaibhavmojidra.roomdemokotlin.database.tables.Subscriber

@Dao
interface SubscriberDAO {

    //Types
    @Insert(onConflict = OnConflictStrategy.IGNORE)//Replaces the duplicate id //For Insert Query
    suspend fun  insertSubscriber(subscriber: Subscriber):Long // Long returns the id generated

//    @Insert //For Insert Query Multiple
//    suspend fun  insertSubscriber(subscriber: Subscriber,subscriber2: Subscriber):List<Long>//Long list returns the ids generated
//
//    @Insert //For Insert Query List
//    suspend fun  insertSubscriber(subscriberList: List<Subscriber>):List<Long>//Long list returns the ids generated

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber):Int //Number of rows updated

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM SUBSCRIBERTABLE") // To make extra query
    suspend fun deleteAll()

    @Query("SELECT * FROM SUBSCRIBERTABLE")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}