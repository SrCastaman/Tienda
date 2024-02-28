package com.example.tienda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorCarro ( var contexto: Context, var carro: ArrayList<Producto>): RecyclerView.Adapter<AdaptadorCarro.MyHolder>(){

    class MyHolder(item: View) : RecyclerView.ViewHolder(item){
        var nombre : TextView
        var precio : TextView
        var imagen : ImageView
        init {
            nombre = item.findViewById(R.id.nombre)
            precio = item.findViewById(R.id.precio)
            imagen = item.findViewById(R.id.imagen)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val vista:View = LayoutInflater.from(contexto).inflate(R.layout.item_recycler_carro,parent,false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return carro.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val producto = carro[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio.toString() + "€"
        Glide.with(contexto).load(producto.imagen).into(holder.imagen)
    }

}