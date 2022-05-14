package com.example.todoapp.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.ToDos
import com.example.todoapp.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Appendable

class ToDosDaoRepository(var application: Application) {
    var todoList:MutableLiveData<List<ToDos>>
    var vt:Veritabani

    init {
        todoList = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application)!!
    }

    fun todoGet() : MutableLiveData<List<ToDos>> {
        return todoList
    }

    fun todoRegister(todo_ad:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newTodo = ToDos(0,todo_ad)
            vt.todoDao().todoAdd(newTodo)
        }
    }

    fun todoUpdate(todo_id:Int, todo_ad: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updatedTodo = ToDos(todo_id,todo_ad)
            vt.todoDao().todoUpdate(updatedTodo)
        }
    }

    fun todoSearch(aramaKelimesi:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            todoList.value = vt.todoDao().todoSearch(aramaKelimesi)
        }
    }

    fun todoDelete(todo_id:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deletedTodo = ToDos(todo_id,"")
            vt.todoDao().todoDelete(deletedTodo)
            takeAllToDos()
        }
    }

    fun takeAllToDos(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            todoList.value = vt.todoDao().allTodos()
        }
    }
}