package com.example.todolist_propio.RVcategorias

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_propio.Data.Categoria
import com.example.todolist_propio.R

class ViewHolderCategorias(view: View):RecyclerView.ViewHolder(view) {

    var categoriaItem:CardView=view.findViewById(R.id.categoriaItem)
    var categoriaTitle:TextView=view.findViewById(R.id.categoriaTitle)
    var categoriaColor:View=view.findViewById(R.id.categoriaColor)

    fun Render(categoria: Categoria, filterTareas:(Int)->Unit){

        when(categoria){
            Categoria.Escuela -> {
                categoriaTitle.text="Escuela"
                categoriaColor.setBackgroundColor(ContextCompat.getColor(categoriaColor.context,R.color.escuela))
            }
            Categoria.Social -> {
                categoriaTitle.text="Social"
                categoriaColor.setBackgroundColor(ContextCompat.getColor(categoriaColor.context,R.color.social))
            }
            Categoria.Trabajo -> {
                categoriaTitle.text="Trabajo"
                categoriaColor.setBackgroundColor(ContextCompat.getColor(categoriaColor.context,R.color.trabajo))
            }
        }

        categoriaItem.setOnClickListener {
            filterTareas(layoutPosition)
        }

    }

}