package com.example.khapharma.Gerant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.khapharma.R
import com.example.khapharma.service.RetrofitFactory
import com.example.khapharma.service.RetrofitService
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class ListeMedicaments : AppCompatActivity() {
    private lateinit var service : RetrofitService
    lateinit var recyclerView: RecyclerView
    var data = BodyProduit()
    lateinit var  btn: Button
    lateinit var  search : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_medicaments)
        service = RetrofitFactory.makeRetrofitService(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyview)
        btn = findViewById(R.id.back)
        btn.setOnClickListener{finish()}
        GlobalScope.launch(Dispatchers.Main) {
            getAllProduit()
        }


    }

    private suspend fun getAllProduit() {
        supervisorScope {
            launch{
                val response = service.allProduit()
                if (response.isSuccessful){
                    println("BONJOUR KHADY ")
                    val res :BodyProduit = response.body()!!
                    println("KHADT")
                    data.addAll(res)
//                        data.forEach { println("ITEM " + it.produit) }
                    recyclerView.adapter  = AdaptateurProduit(data,service)
                    recyclerView.visibility = View.VISIBLE
                }

            }
        }

    }


//    override fun  cherche(){
//        search = findViewById(R.id.Searview)
//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: BodyProduitItem?): Boolean {
//                search.clearFocus()
//                if (data !=null){
//                    AdaptateurProduit.filter.filter(query)
//                }
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//            }
//        })
//
//    }
}