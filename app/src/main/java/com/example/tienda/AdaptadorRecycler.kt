package com.example.tienda

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar


class AdaptadorRecycler(var contexto: Context, var btnCarro : Button, var lista:ArrayList<Producto>, var carro:ArrayList<Producto>): RecyclerView.Adapter<AdaptadorRecycler.MyHolder>() {


    init {
        lista = ArrayList()
        carro = ArrayList()
    }
    private var listaCompleta: ArrayList<Producto> = lista
    private var posicion = 1




    class MyHolder(item: View) : ViewHolder(item){
        var nombre : TextView
        var precio : TextView
        var imagen : ImageView
        var boton : Button

        init {
            nombre = item.findViewById(R.id.nombre)
            precio = item.findViewById(R.id.precio)
            imagen = item.findViewById(R.id.imagen)
            boton = item.findViewById(R.id.button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorRecycler.MyHolder {

        val vista:View = LayoutInflater.from(contexto).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun onBindViewHolder(holder: AdaptadorRecycler.MyHolder, position: Int) {
        val producto = lista[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio.toString() + "€"
        Glide.with(contexto).load(producto.imagen).into(holder.imagen)
        holder.boton.setOnClickListener {
            añadirCarro(position)

        }

        btnCarro.setOnClickListener {
            val intent = Intent(contexto, SecondActivity::class.java)
            if(!carro.equals(null)) {
                intent.putExtra("carro", carro)
                contexto.startActivity(intent)
            } else{

            }
        }


    }


    override fun getItemCount(): Int {
        return lista.size
    }

    fun añadirCarro(position: Int){
        carro.add(lista[position])
    }

    fun devolverCarro() : ArrayList<Producto>{
        return carro
    }

    fun addProducto(producto: Producto){
        this.lista.add(producto)
        notifyItemInserted(lista.size-1)
    }

    fun filtrarLista(categoria: String){
        if(categoria.equals("Todos")){
            this.lista = listaCompleta
        } else {
            this.lista = listaCompleta.filter {
                it.categoria.equals(categoria,true)
            } as ArrayList<Producto>
        }
        notifyDataSetChanged()
    }





}
