package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.todoapp.repo.ToDosDaoRepository

class ToDoDetailFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val krepo = ToDosDaoRepository(application)

    fun update(todo_id:Int,todo_ad:String){
        krepo.todoUpdate(todo_id,todo_ad)
    }
}