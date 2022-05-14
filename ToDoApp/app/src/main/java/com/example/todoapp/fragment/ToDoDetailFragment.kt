package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodoDetailBinding
import com.example.todoapp.viewmodel.ToDoDetailFragmentViewModel
import com.example.todoapp.viewmodel.ToDoDetailVMF
import com.example.todoapp.viewmodel.ToDoRegisterVMF

class ToDoDetailFragment : Fragment() {
    private lateinit var tasarim:FragmentTodoDetailBinding
    private lateinit var viewModel:ToDoDetailFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_todo_detail, container, false)
        tasarim.todoDetailFragment = this
        tasarim.todoDetayToolbarBaslik = "Yapılacak İş Detay"

        val bundle:ToDoDetailFragmentArgs by navArgs()
        val gelenKisi = bundle.kisi
        tasarim.todoNesnesi = gelenKisi

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:ToDoDetailFragmentViewModel by viewModels(){
            ToDoDetailVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonGuncelle(todo_id:Int, todo_ad:String){
        viewModel.update(todo_id,todo_ad)
    }
}