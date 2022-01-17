package com.example.khapharma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.khapharma.pharmacien.Compte

class Welcome : AppCompatActivity() {
    lateinit var connexionButton : AppCompatButton
    lateinit var CompteButton : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        connexionButton = findViewById(R.id.appCompatButton)
//        CompteButton = findViewById(R.id.appCompatButton1)

        connexionButton.setOnClickListener {
            val  PageLogin = Intent(this,Login::class.java)
            startActivity(PageLogin)
        }
//        CompteButton.setOnClickListener {
//            val  PageRegiste = Intent(this, Compte::class.java)
//            startActivity(PageRegiste)
//        }
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu,menu)
//        return true
//    }
}