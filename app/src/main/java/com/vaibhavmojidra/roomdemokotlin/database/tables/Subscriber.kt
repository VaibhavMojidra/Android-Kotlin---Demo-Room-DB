package com.vaibhavmojidra.roomdemokotlin.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SubscriberTable") //Table Name
data class Subscriber(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    @ColumnInfo(name="Subscriber_Email")//Give another name to Column
    var email:String
)