package com.vaibhavmojidra.roomdemokotlin.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaibhavmojidra.roomdemokotlin.database.dao.SubscriberDAO
import com.vaibhavmojidra.roomdemokotlin.database.tables.Subscriber

@Database(entities = [Subscriber::class],version = 1,exportSchema = false)
abstract class SubscriberDatabase :RoomDatabase(){
    abstract val subscriberDAO:SubscriberDAO
    companion object{
        @Volatile
        private var subscriberDatabase:SubscriberDatabase?=null
        fun getInstance(context:Context):SubscriberDatabase{
            synchronized(this){
                var instance= subscriberDatabase
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                    SubscriberDatabase::class.java,
                    "SubscriberDatabase").build()
                }
                return instance
            }
        }
    }
}