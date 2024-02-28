package com.example.tienda

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tienda.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var adaptadorCarro : AdaptadorCarro
    private lateinit var carro: ArrayList<Producto>


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide.with(this).load("https://cdn-icons-png.flaticon.com/512/4083/4083166.png").into(binding.imageView2)
        carro = ArrayList()
        carro = intent.getSerializableExtra("carro") as ArrayList<Producto>
        setUpRecyclerView()
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
        }
        binding.btnComprar.setOnClickListener {
            finalizarCompra(carro)
        }
    }


    fun setUpRecyclerView(){
        binding.recyclerCarro.layoutManager = LinearLayoutManager(this)
        adaptadorCarro = AdaptadorCarro(this, carro)
        binding.recyclerCarro.adapter = adaptadorCarro
        adaptadorCarro.notifyDataSetChanged()
    }
    fun finalizarCompra(carro: ArrayList<Producto>){
        var precioTotal = 0
        for (i in 0 until carro.size){
            precioTotal += carro[i].precio
        }
        Snackbar.make(binding.root, "Enhorabuena, compra por valor de ${precioTotal}€ realizada", Snackbar.LENGTH_SHORT).show()
    }



}










