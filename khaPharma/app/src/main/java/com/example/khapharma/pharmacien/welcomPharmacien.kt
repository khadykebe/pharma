package com.example.khapharma.pharmacien

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.example.khapharma.Gerant.Commande
import com.example.khapharma.Gerant.ListeMedicaments
import com.example.khapharma.Login
import com.example.khapharma.R
import com.example.khapharma.Venteur.Inventaire
import com.example.khapharma.Venteur.OperationVente
import com.example.khapharma.service.SharedPref
import java.util.logging.Level.INFO
import kotlin.system.exitProcess

class welcomPharmacien : AppCompatActivity() {
    lateinit var stockView : CardView
    lateinit var venteView : CardView
    lateinit var userView : CardView
    lateinit var commandeView : CardView
    lateinit var nomComplet : AppCompatTextView
    lateinit var username : AppCompatTextView
    lateinit var  role:AppCompatTextView

    override fun onOptionsItemSelected(item:MenuItem): Boolean {
        when(item.itemId){
            R.id.quit ->{
                println("55555555555555555555555555555555555555555555555555555555")
                val  builder = AlertDialog.Builder(this)
                builder.setTitle("Question").setMessage("Voulez-vous vraiment quitter")
                    .setPositiveButton(
                        "oui",
                        DialogInterface.OnClickListener{dialog, which ->
                            finish()
                            exitProcess(0)
                        }).setNegativeButton("Non",null)
                val dialog = builder.create()

                return true
            }
        }
       return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom_pharmacien)

        stockView = findViewById(R.id.stock)
        venteView = findViewById(R.id.vente)
        userView = findViewById(R.id.user)
        commandeView = findViewById(R.id.commande)

        stockView.setOnClickListener {
            val  PageLogin = Intent(this, Stock::class.java)
            startActivity(PageLogin)
        }
        venteView.setOnClickListener {
            val  PageLogin = Intent(this,OperationVente::class.java)
            startActivity(PageLogin)
        }


        userView.setOnClickListener {
            val  PageLogin = Intent(this,gestionUSer::class.java)
            startActivity(PageLogin)
        }
        commandeView.setOnClickListener {
            val  PageLogin = Intent(this,Commande::class.java)
            startActivity(PageLogin)
        }

        stockView.visibility = View.GONE
        userView.visibility = View.GONE
        venteView.visibility = View.GONE
        commandeView.visibility = View.GONE

        role=findViewById(R.id.role)
        nomComplet = findViewById(R.id.nomComplet)
        username = findViewById(R.id.username)

        val s = SharedPref()
        s.getUserRoles(this@welcomPharmacien)?.let { role.text = it }
        s.getUserFullName(this@welcomPharmacien).let { nomComplet.text = it }
        s.getUser(this@welcomPharmacien)?.let { username.text = it }
        if (s.getUserRoles(this@welcomPharmacien) =="pharmacien") {

            stockView.visibility = View.VISIBLE
            userView.visibility = View.VISIBLE
            venteView.visibility = View.VISIBLE
            commandeView.visibility = View.VISIBLE
        }

        else if(s.getUserRoles(this@welcomPharmacien) =="gérant") {

            stockView.visibility = View.VISIBLE
            venteView.visibility = View.VISIBLE
            commandeView.visibility = View.VISIBLE
        }

        else if(s.getUserRoles(this@welcomPharmacien) =="vendeur") {

            venteView.visibility = View.VISIBLE
            commandeView.visibility = View.VISIBLE
        }


    }

}