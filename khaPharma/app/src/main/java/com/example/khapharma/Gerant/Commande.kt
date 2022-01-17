package com.example.khapharma.Gerant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.khapharma.R
import org.jetbrains.anko.webView

class Commande : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commande)
        webView().settings.javaScriptEnabled = true
        webView().webViewClient = WebViewClient()
        webView().loadUrl("https://senegal.ubipharm.com/RecapCommande")
    }
}