package com.example.khapharma.Gerant

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khapharma.R
import com.example.khapharma.models.BodyProduitItem
import com.example.khapharma.service.RetrofitService

class AdaptateurProduit (private val listeProduit:ArrayList<BodyProduitItem>, var service: RetrofitService):
    RecyclerView.Adapter<AdaptateurProduit.ViewHolder>(){
//        var listeData :MutableList<BodyProduit> = listeProduit as MutableList<BodyProduit>
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptateurProduit.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.produit,parent,false)
            return AdaptateurProduit.ViewHolder(view)
        }


        class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
            val nom : AppCompatTextView = itemView.findViewById(R.id.nom)
            val prenom : AppCompatTextView = itemView.findViewById(R.id.prixP)

        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val ItemsViewModel = listeProduit[position]
            Log.e("CREATE ITEM", "onBindViewHolder: $ItemsViewModel")
            holder.nom.text = ItemsViewModel.nom
            holder.prenom.text = "prix Unitaire : " + ItemsViewModel.prixP


        }

        override fun getItemCount(): Int {
            return listeProduit.size;
        }




    }

