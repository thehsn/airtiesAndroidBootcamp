package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodoRegisterBinding
import com.example.todoapp.viewmodel.ToDoRegisterFragmentViewModel
import com.example.todoapp.viewmodel.ToDoRegisterVMF

class ToDoRegisterFragment : Fragment() {
    private lateinit var tasarim:FragmentTodoRegisterBinding
    private lateinit var viewModel:ToDoRegisterFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_todo_register, container, false)
        tasarim.todoRegisterFragment = this
        tasarim.todoRegisterToolbarBaslik = "Yapılacak İş Kayıt"
        return tasarim.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:ToDoRegisterFragmentViewModel by viewModels(){
            ToDoRegisterVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonKaydetTikla(todo_ad:String){
        viewModel.register(todo_ad)
    }
}