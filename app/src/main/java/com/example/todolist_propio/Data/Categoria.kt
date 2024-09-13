package com.example.todolist_propio.Data

sealed class Categoria(var nombre:String,var isSelected:Boolean) {
    object Escuela: Categoria("Escuela",false)
    object Trabajo: Categoria("Trabajo",false)
    object Social:Categoria("Social",false)
}