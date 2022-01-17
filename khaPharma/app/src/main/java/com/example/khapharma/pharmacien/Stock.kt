package com.example.khapharma.pharmacien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.khapharma.R
import com.example.khapharma.models.AllStock
import com.example.khapharma.modelStock.CustomAdapter
import com.example.khapharma.service.RetrofitFactory
import com.example.khapharma.service.RetrofitService
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class Stock : AppCompatActivity(){
    private lateinit var service :RetrofitService
    lateinit var recyclerView: RecyclerView
    lateinit var ajoutStock :Button
    var data = AllStock()
//    lateinit var leayoutManager: RecyclerView.LayoutManager

    lateinit var retour : Button
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)
        ajoutStock = findViewById(R.id.ajout)
        retour = findViewById(R.id.back)
        retour.setOnClickListener{this.onclick()}
        service = RetrofitFactory.makeRetrofitService(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyview)
        GlobalScope.launch(Dispatchers.Main) {
            getAllStock()
        }
        ajoutStock.setOnClickListener{
            val pageAjout = Intent(this,AjoutStock::class.java)
            startActivity(pageAjout)
        }

    }

    private suspend fun getAllStock(){
        supervisorScope {
            launch{
                val response = service.allStock()
                if (response.isSuccessful){
                    println("BONJOUR KHADY ")
                    val res = response.body()
                    if (res != null) {
//                        println(res)
                        data.addAll(res)
//                        data.forEach { println("ITEM " + it.produit) }
                        recyclerView.adapter  = CustomAdapter(data,service)
                        recyclerView.visibility = View.VISIBLE
                    }
                }

            }
        }

    }

    private fun onclick(){
        finish();
    }
}