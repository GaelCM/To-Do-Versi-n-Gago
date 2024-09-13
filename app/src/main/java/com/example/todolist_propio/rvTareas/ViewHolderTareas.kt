package com.example.todolist_propio.rvTareas

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_propio.Data.Categoria
import com.example.todolist_propio.Data.Tarea
import com.example.todolist_propio.R

class ViewHolderTareas(view: View):RecyclerView.ViewHolder(view){

    var tareasItem:CardView=view.findViewById(R.id.tareasItem)
    var tareaTitle:TextView=view.findViewById(R.id.tareaTitle)
    var tareaColor:CheckBox=view.findViewById(R.id.tareaColor)

    fun Render(tarea: Tarea){

        tareaTitle.text=tarea.nombre

        var color=when(tarea.categoria){
            Categoria.Escuela -> {
                ContextCompat.getColor(tareaColor.context,R.color.escuela)
            }
            Categoria.Social -> {
                ContextCompat.getColor(tareaColor.context,R.color.social)
            }
            Categoria.Trabajo ->{
                ContextCompat.getColor(tareaColor.context,R.color.trabajo)
            }
        }
        tareaColor.buttonTintList= ColorStateList.valueOf(color)

        when(tarea.isSelected){
            true -> {
                tareasItem.setBackgroundColor(ContextCompat.getColor(tareasItem.context,R.color.gris))
                tareaColor.isChecked=tarea.isSelected
                tareaTitle.paintFlags=tareaTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            false -> {
                tareaColor.isChecked=tarea.isSelected
                tareaTitle.paintFlags=tareaTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                tareasItem.setBackgroundColor(ContextCompat.getColor(tareasItem.context,R.color.white))
            }
        }


    }
}