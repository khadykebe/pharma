package com.example.khapharma.Gerant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.khapharma.Login
import com.example.khapharma.R
import com.example.khapharma.models.LoginBody
import com.example.khapharma.models.User
import com.example.khapharma.pharmacien.welcomPharmacien
import com.example.khapharma.service.RetrofitFactory
import com.example.khapharma.service.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class welcomGerant : AppCompatActivity() {
    lateinit var mail : AppCompatEditText
    lateinit var pass:AppCompatEditText
    lateinit var  btn : AppCompatButton
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom_gerant)
        mail= findViewById(R.id.mail)
        pass = findViewById(R.id.pass)
        btn= findViewById(R.id.send)
        btn.setOnClickListener{this.changeMot()}
    }

    private fun changeMot() {
        val email = mail.text.toString()
        val password = pass.text.toString()
        val service = RetrofitFactory.makeRetrofitService(this)
        if (loginValidate(email, password)) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.forgot_password(LoginBody(email,password))
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful){
                        val res  :User? = response.body()
                        println("BONJOUR KHADY")

                        Toast.makeText(this@welcomGerant,"mot de passe changer avec succé", Toast.LENGTH_SHORT).show()
                        val  intent = Intent(this@welcomGerant, Login::class.java)
                        val s = Sharedfreflogin()
                        res?.let { s.saveUserDate(this@welcomGerant, it);
                            startActivity(intent)
                        }
                    }
                }

            }

        }
    }

    private fun loginValidate(email: String, password: String): Boolean {
        if(email.isEmpty() || password.isEmpty()){
            return false;
        }
        return true;
    }
}