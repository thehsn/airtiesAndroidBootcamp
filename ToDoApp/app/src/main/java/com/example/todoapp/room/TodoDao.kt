package com.example.todoapp.room

import androidx.room.*
import com.example.todoapp.entity.ToDos

@Dao
interface TodoDao {

    @Query("SELECT * FROM yapilacaklar")
    suspend fun allTodos() : List<ToDos>

    @Insert
    suspend fun todoAdd(todo:ToDos)

    @Update
    suspend fun todoUpdate(todo: ToDos)

    @Delete
    suspend fun todoDelete(todo: ToDos)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%'|| :aramaKelimesi ||'%'")
    suspend fun todoSearch(aramaKelimesi:String) : List<ToDos>
}