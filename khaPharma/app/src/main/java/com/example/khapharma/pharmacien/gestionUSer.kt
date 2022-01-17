package com.example.khapharma.pharmacien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.khapharma.R
import com.example.khapharma.models.AllUser
import com.example.khapharma.modelC.MonAdaptateur
import com.example.khapharma.service.RetrofitFactory
import com.example.khapharma.service.RetrofitService
import kotlinx.coroutines.*


@DelicateCoroutinesApi
class gestionUSer : AppCompatActivity() {
    lateinit var  button : Button
    lateinit var btn : Button
    private lateinit var service : RetrofitService
    lateinit var recyclerView: RecyclerView
    var data = AllUser()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_user)
        button = findViewById(R.id.back)
        btn = findViewById(R.id.ajout)
        button.setOnClickListener{finish()}
        service = RetrofitFactory.makeRetrofitService(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyview)
        GlobalScope.launch(Dispatchers.Main) {
            getAllUser()
        }
        btn.setOnClickListener{
            val intent = Intent(this,Compte::class.java)
            startActivity(intent)
        }
    }
    private suspend fun getAllUser(){
        supervisorScope {
            launch{
                val response = service.allUser()
                if(response.isSuccessful){
                    val res = response.body()
                    println(res)
                    if (res != null){
                        data.addAll(res)
//                        data.forEach {println("ITEM " + it.produit) }
                        recyclerView.adapter  = MonAdaptateur(data,service)
                        recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}