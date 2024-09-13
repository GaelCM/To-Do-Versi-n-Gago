package com.example.todolist_propio.RVcategorias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_propio.Data.Categoria
import com.example.todolist_propio.R

class AdapterCategorias(private var categoriasList: List<Categoria>, private var filterTares:(Int)->Unit):RecyclerView.Adapter<ViewHolderCategorias>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategorias {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.categoria_item,parent,false)
        return ViewHolderCategorias(view)

    }

    override fun getItemCount(): Int {
        return categoriasList.size
    }

    override fun onBindViewHolder(holder: ViewHolderCategorias, position: Int) {

        holder.Render(categoriasList[position],filterTares)

    }
}