package com.example.khapharma.pharmacien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.khapharma.R
import com.example.khapharma.models.AllStock
import com.example.khapharma.models.ajout
import com.example.khapharma.service.RetrofitFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
class AjoutStock : AppCompatActivity() {
    lateinit var retour: Button
    lateinit var  produitView : TextInputEditText
    lateinit var  qteView : TextInputEditText
    lateinit var  dateView : TextInputEditText
    lateinit var boutonAjout : AppCompatButton

//    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_stock)
        retour=findViewById(R.id.back)
        produitView = findViewById(R.id.produit)
        qteView = findViewById(R.id.qte)
        dateView = findViewById(R.id.date)
        boutonAjout = findViewById(R.id.bouton)
        boutonAjout.setOnClickListener{this.ajoutProduit()}
        retour.setOnClickListener{this.onclick()}

    }

    @DelicateCoroutinesApi
    private fun ajoutProduit() {
        val produit = produitView.text.toString()
        val qteEntrer = qteView.text.toString()
        val date = dateView.text.toString()

        if(valide(produit,qteEntrer,date)){
            val service = RetrofitFactory.makeRetrofitService(this)
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.addStock(ajout(produit, qteEntrer,date))
                withContext(Dispatchers.Main){

                        if(response.isSuccessful){
                            val res: AllStock? = response.body()
                            println("BONJOUR KHADY")
                            println(res)
                            Toast.makeText(this@AjoutStock,"un produit est ajouter",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@AjoutStock,Stock::class.java)
                            startActivity(intent)
                        }



                }
            }
        }

    }

    private fun valide(produit:String,qteEntrer:String,date: String): Boolean {
        if(produit.isEmpty() || qteEntrer.isEmpty() || date.isEmpty()){
            return false;
        }
        return true
    }

    private fun onclick(){
        finish();
    }
}


