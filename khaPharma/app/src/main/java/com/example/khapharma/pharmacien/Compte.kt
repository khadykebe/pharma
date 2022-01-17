package com.example.khapharma.pharmacien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.khapharma.R
import com.example.khapharma.SessionManager
import com.example.khapharma.models.CompteBody
import com.example.khapharma.service.RetrofitFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Compte : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
//        lateinit var autotextView : AutoCompleteTextView
    lateinit var retour : Button
    lateinit var nomView : TextInputEditText
    lateinit var prenomView : TextInputEditText
    lateinit var adresseView : TextInputEditText
    lateinit var telephoneView : TextInputEditText
    lateinit var emailView : TextInputEditText
    lateinit var passwordView : TextInputEditText
    lateinit var  compteBTN: Button
    lateinit var  spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compte)
        spinner = findViewById(R.id.spinner)
        retour = findViewById(R.id.back)
        retour.setOnClickListener{finish()}
        val role = resources.getStringArray(R.array.role)
        val adaptateur = ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,role)
        spinner.adapter = adaptateur
        spinner.prompt = "selection un role"

        nomView = findViewById(R.id.nom)
        prenomView = findViewById(R.id.prenom)
        adresseView = findViewById(R.id.adresse)
        telephoneView = findViewById(R.id.telephone)
        emailView = findViewById(R.id.email)
        compteBTN = findViewById(R.id.button1)
        passwordView = findViewById(R.id.password)
        compteBTN.setOnClickListener { this.creerCompte() }


    }

    private fun creerCompte() {
        val nom = nomView.text
        val prenom = prenomView.text
        val adresse = adresseView.text
        val telephone = telephoneView.text
        val email = emailView.text
        val password = passwordView.text
        val roles = spinner.selectedItem

        if(creerCValide(nom.toString(),prenom.toString(),adresse.toString(),telephone.toString(),email.toString(),password.toString(),
                roles as String
            )){
            val service = RetrofitFactory.makeRetrofitService(this)
            CoroutineScope(Dispatchers.IO).launch{
                withContext(Dispatchers.Main){

                        val response = service.add_user(CompteBody(nom.toString(),prenom.toString(),adresse.toString(),telephone.toString(),email.toString(),password.toString(),roles.toString()))
                        if(response.isSuccessful){
                            val  res: String? = response.body()
                            println(res)
                            Toast.makeText(this@Compte,"compte creer",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Compte,gestionUSer::class.java)
                            startActivity(intent)
                            sessionManager = SessionManager(this@Compte)
                            res.let {
                                if (it != null) {
                                    sessionManager.saveAuthToken(it)
                                }
                            }

                        }

                }
            }

        }




    }

    fun creerCValide(
        nom: String, prenom: String, adresse: String, telephone: String, email: String, password: String,
        role: String
    ): Boolean {

        if(nom.isEmpty() || prenom.isEmpty()|| adresse.isEmpty()|| telephone.isEmpty()|| email.isEmpty()|| password.isEmpty() || role.isEmpty()){
            return false;
        }
        return true;
    }







}


