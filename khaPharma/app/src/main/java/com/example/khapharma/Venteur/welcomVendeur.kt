package com.example.khapharma.Venteur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.khapharma.R
import com.example.khapharma.models.ProduitBody
import com.example.khapharma.models.Resproduit
import com.example.khapharma.service.RetrofitFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
@DelicateCoroutinesApi
class welcomVendeur : AppCompatActivity() {
    lateinit var prixV : TextInputEditText
    lateinit var nomView : TextInputEditText
    lateinit var boutton : Button
    lateinit var  btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom_vendeur)

        prixV = findViewById(R.id.prixU)
        nomView = findViewById(R.id.nom)
        boutton = findViewById(R.id.add)
        boutton.setOnClickListener{this.AddProduit()}
        btn = findViewById(R.id.back)
        btn.setOnClickListener{finish()}
    }

    @DelicateCoroutinesApi
    private  fun AddProduit(){
        val prixU = prixV.text.toString()
        val nom = nomView.text.toString()

        if (valider(prixU,nom)){
            val service = RetrofitFactory.makeRetrofitService(this)
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    val response = service.newProduit(ProduitBody(nom,prixU))
                    if (response.isSuccessful){
                        println("BONJOUR KHADY")
                        val res:String? = response.body()
                        println(res)
                        Toast.makeText(this@welcomVendeur,"un produit  ajouter", Toast.LENGTH_SHORT).show()
                    }

                }
            }


        }
    }

    private fun valider(prixU: String, nom: String): Boolean {
        if(prixU.isEmpty() || nom.isEmpty()){
            return false

        }
        return true
    }


}