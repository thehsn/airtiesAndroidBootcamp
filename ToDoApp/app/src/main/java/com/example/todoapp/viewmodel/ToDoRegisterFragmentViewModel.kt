package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.todoapp.repo.ToDosDaoRepository

class ToDoRegisterFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val krepo = ToDosDaoRepository(application)

    fun register(todo_ad:String){
        krepo.todoRegister(todo_ad)
    }
}