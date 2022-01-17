package com.example.khapharma.Venteur

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.khapharma.Gerant.ListeMedicaments
import com.example.khapharma.R
import com.example.khapharma.models.VenteBody
import com.example.khapharma.pharmacien.gestionUSer
import com.example.khapharma.service.RetrofitFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.INTEGER
import java.lang.reflect.Modifier.toString
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Arrays.toString
import java.util.Objects.toString
import kotlin.system.exitProcess

class OperationVente : AppCompatActivity() {
    lateinit var retour : Button
    lateinit var  qteView: TextInputEditText
    lateinit var verseView:TextInputEditText
    lateinit var  produitView : TextInputEditText
    lateinit var  list : AppCompatButton
    lateinit var montant : TextView
    lateinit var rendu :TextView
    lateinit var facture :TextView
    lateinit var  vertP : Button
    lateinit var acheteB: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation_vente)
        retour = findViewById(R.id.back)
        retour.setOnClickListener { this.onclick() }
        qteView = findViewById(R.id.qte)
        verseView = findViewById(R.id.verse)
        produitView = findViewById(R.id.produit)
        acheteB = findViewById(R.id.acheter)
        acheteB.setOnClickListener { this.newVente() }
        montant = findViewById(R.id.montant)
        rendu = findViewById(R.id.rendue)
        facture = findViewById(R.id.facture)
        facture.setOnClickListener{this.dialogue()}
        vertP = findViewById(R.id.ajproduit)
        vertP.setOnClickListener{
            val intent = Intent(this,welcomVendeur::class.java)
            startActivity(intent)
        }
        list = findViewById(R.id.listeProduit)
        list.setOnClickListener{
            val intent  = Intent(this, ListeMedicaments::class.java)
            startActivity(intent)
        }

    }




    private fun newVente() {
        val qte = qteView.text.toString()
        val verse = verseView.text.toString()
        val produit = produitView.text.toString()

        montant.visibility = View.GONE
        rendu.visibility = View.GONE
        facture.visibility = View.GONE

        if(valiude(qte,verse,produit)){
            val service = RetrofitFactory.makeRetrofitService(this)
            CoroutineScope(Dispatchers.IO).launch {
                CoroutineScope(Dispatchers.IO).launch {
                    val  response = service.NewVente(VenteBody(qte,verse,produit))
                    if (response.isSuccessful){
                        val res : Int? = response.body()
                        println(res)
                        this@OperationVente.runOnUiThread(java.lang.Runnable{
                            montant.visibility = View.VISIBLE;
                            rendu.visibility = View.VISIBLE
                            facture.visibility = View.VISIBLE
                            rendu.text = res.toString()
                        })
                    }
                }
            }
        }
    }

    private fun valiude(qte: String, verse: String, produit: String): Boolean {
        if (qte.isEmpty() || verse.isEmpty() || produit.isEmpty())
            return false
        return true
    }

    private fun onclick(){
        finish();
    }
    @SuppressLint("SimpleDateFormat")
    private fun dialogue() {
        val total = Integer.valueOf(verseView.text.toString()) - Integer.valueOf(rendu.text.toString())
        val rnd = Math.random() * 10
//        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDateTimeString: String = DateFormat.getDateTimeInstance().format(Date())
        val builder = AlertDialog.Builder(this)
        builder.setTitle("votre facture").setMessage("PARMACIE DU RAIL  " +"\n"+
                "ROUTE DES HLM RUFISQUE" +"\n"+
                "TEL : 33 836 78 65" +"\n"+
                "code client : ${rnd.toInt()}" +"\n"+
                "date : ${currentDateTimeString}" +"\n"+
                "code facture : ${rnd.toInt()}" +"\n"+
                "Qte: ${qteView.text.toString()}"+"\n"+
                "Designation : ${produitView.text.toString()}"+"\n"+
                "PT : ${total}" +"\n"+
                "Montant Vercé : ${ verseView.text.toString()}" +"\n"+
                "Montant Rendu : ${rendu.text}" +"\n"+
                "Vérifier Votre monnaie")
            .setPositiveButton("ok" , DialogInterface.OnClickListener{
                    dialog, which ->
                finish()
                exitProcess(0)
            })
        val dialog =builder.create()
        dialog.show()
    }

}