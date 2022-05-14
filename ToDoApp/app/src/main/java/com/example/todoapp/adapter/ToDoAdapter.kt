package com.example.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.CardTasarimBinding
import com.example.todoapp.entity.ToDos
import com.example.todoapp.fragment.HomepageFragmentDirections
import com.example.todoapp.viewmodel.HomepageFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class ToDoAdapter(var mContext:Context,
                  var todosList:List<ToDos>,
                  var viewModel:HomepageFragmentViewModel)
    : RecyclerView.Adapter<ToDoAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:CardTasarimBinding = DataBindingUtil.inflate(layoutInflater,R.layout.card_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val todo = todosList.get(position)
        val t = holder.tasarim

        t.todoNesnesi = todo

        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it,"${todo.todo_ad} silinsin mi?",Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.sil(todo.todo_id)
                }
                .show()
        }

        t.satirCard.setOnClickListener {
            val gecis = HomepageFragmentDirections.kisiDetayGecis(kisi = todo)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return todosList.size
    }
}