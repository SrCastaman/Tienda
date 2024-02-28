package com.example.tienda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.tienda.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lista: ArrayList<Producto>
    private lateinit var carro: ArrayList<Producto>
    private lateinit var adaptadorRecycler: AdaptadorRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        lista = ArrayList()
        carro = ArrayList()
        rellenarLista()
        setContentView(binding.root)
        Glide.with(this).load("https://cdn-icons-png.flaticon.com/512/3144/3144456.png").into(binding.imageView)
        adaptadorRecycler = AdaptadorRecycler(this, binding.btnCarrito,lista,carro)
        binding.recyclerProductos.adapter = adaptadorRecycler
        binding.recyclerProductos.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)

        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
                ){
                val seleccionado = parent!!.adapter.getItem(position).toString()
                adaptadorRecycler.filtrarLista(seleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("Not yet implemented")
            }

        }
        binding.btnCarrito.setOnClickListener {
            carro = adaptadorRecycler.devolverCarro()
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("carro",carro)
            startActivity(intent)

        }
    }



    fun rellenarLista(){
        val peticion : JsonObjectRequest =
            JsonObjectRequest(Request.Method.GET,"https://dummyjson.com/products",null, {
                val products: JSONArray = it.getJSONArray("products")
                for(i in 0 until products.length()){
                    val product = products.getJSONObject(i)
                    adaptadorRecycler.addProducto(
                        Producto(product.getString("title"),
                            product.getInt("price"),
                            product.getString("thumbnail"),
                            product.getString("category"))
                    )
                }


            },{
                Snackbar.make(binding.root, "Error en la conexión", Snackbar.LENGTH_SHORT).show()
            })
        Volley.newRequestQueue(this).add(peticion)
    }




}