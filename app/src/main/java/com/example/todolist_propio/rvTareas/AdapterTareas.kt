package com.example.todolist_propio.rvTareas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_propio.Data.Tarea
import com.example.todolist_propio.R

class AdapterTareas(var ListaTareas:List<Tarea>, private var setIsSelected:(Int)->Unit):RecyclerView.Adapter<ViewHolderTareas>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTareas {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.tareas_item,parent,false)
        return ViewHolderTareas(view)

    }

    override fun getItemCount(): Int {
        return ListaTareas.size
    }

    override fun onBindViewHolder(holder: ViewHolderTareas, position: Int) {
        holder.Render(ListaTareas[position])

        holder.tareaColor.setOnCheckedChangeListener { compoundButton, b ->
            setIsSelected(position)
        }

    }
}