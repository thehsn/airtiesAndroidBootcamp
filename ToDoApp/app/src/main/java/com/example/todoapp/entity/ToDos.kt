package com.example.todoapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "yapilacaklar")
data class ToDos(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "yapilacak_id") @NotNull var todo_id:Int,
                 @ColumnInfo(name = "yapilacak_is") @NotNull var todo_ad:String) : Serializable {
}