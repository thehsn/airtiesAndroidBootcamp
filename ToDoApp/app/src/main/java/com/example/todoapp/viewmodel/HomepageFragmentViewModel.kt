package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.entity.ToDos
import com.example.todoapp.repo.ToDosDaoRepository

class HomepageFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val krepo = ToDosDaoRepository(application)
    var todosListesi = MutableLiveData<List<ToDos>>()

    init {
        LoadTodos()
        todosListesi = krepo.todoGet()
    }

    fun ara(aramaKelimesi:String){
        krepo.todoSearch(aramaKelimesi)
    }

    fun sil(todo_id:Int){
        krepo.todoDelete(todo_id)
    }

    fun LoadTodos(){
        krepo.takeAllToDos()
    }
}