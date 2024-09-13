package com.example.todolist_propio

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.todolist_propio.Data.Categoria
import com.example.todolist_propio.Data.Tarea
import com.example.todolist_propio.RVcategorias.AdapterCategorias
import com.example.todolist_propio.rvTareas.AdapterTareas
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var DataList:MutableList<Tarea>
    private lateinit var CategoriaList:List<Categoria>
    private lateinit var rvCategorias:RecyclerView
    private lateinit var adapCategorias:AdapterCategorias
    private lateinit var rvTareas:RecyclerView
    private lateinit var adapterTareas: AdapterTareas
    private lateinit var addTareaBtn:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()


    }

    fun initComponents(){
        rvCategorias=findViewById(R.id.rvCategorias)
        rvTareas=findViewById(R.id.rvTareas)
        addTareaBtn=findViewById(R.id.addTareabtn)

        DataList=getTareas()
        CategoriaList=getCategorias()

        adapCategorias=AdapterCategorias(CategoriaList,{id->filterTareas(id)})
        rvCategorias.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategorias.adapter=adapCategorias

        adapterTareas=AdapterTareas(DataList,{ id ->setIsSelected(id) })
        rvTareas.layoutManager=LinearLayoutManager(this)
        rvTareas.adapter=adapterTareas

    }
    fun initListeners() {
        addTareaBtn.setOnClickListener {
            var dialog=Dialog(this)
            dialog.setContentView(R.layout.dialog_add_tarea)

            var tareaTitle:EditText=dialog.findViewById(R.id.newTarea)
            var categoriaSection:RadioGroup=dialog.findViewById(R.id.categoriaSection)
            var saveTareaBtn:Button=dialog.findViewById(R.id.saveTareaBtn)

            saveTareaBtn.setOnClickListener {
                var idCategoria=categoriaSection.checkedRadioButtonId
                var categoriaReference:RadioButton=categoriaSection.findViewById(idCategoria)
                var categoriaValue=when(categoriaReference.text){
                    "Escuela"->Categoria.Escuela
                    "Trabajo"->Categoria.Trabajo
                    else -> Categoria.Social
                }

                DataList.add(Tarea(tareaTitle.text.toString(),categoriaValue))
                updateListaData()
                dialog.hide()
            }

            dialog.show()
        }
    }

    fun getCategorias():List<Categoria>{
        var listaCategorias= listOf(Categoria.Escuela,Categoria.Trabajo,Categoria.Social)
        return listaCategorias
    }

    fun getTareas():MutableList<Tarea>{
        var listaCategorias= mutableListOf(Tarea("Sacar fotos en CFE",Categoria.Trabajo),Tarea("Actualizar sitio de innovatecNM",Categoria.Escuela))
        return listaCategorias
    }

    fun setIsSelected(id:Int){
        DataList[id].isSelected=!DataList[id].isSelected
        adapterTareas.notifyDataSetChanged()
    }

    fun filterTareas(id: Int){
        CategoriaList[id].isSelected=!CategoriaList[id].isSelected
        updateListaData()

    }
    fun updateListaData(){
        var filtroCategoria:List<Categoria> = CategoriaList.filter { it.isSelected }
        var listaFiltrada:List<Tarea> =DataList.filter { filtroCategoria.contains(it.categoria) }
        adapterTareas.ListaTareas=listaFiltrada
        adapterTareas.notifyDataSetChanged()
    }

}