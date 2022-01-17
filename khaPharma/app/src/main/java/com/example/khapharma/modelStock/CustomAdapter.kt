package com.example.khapharma.modelStock

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khapharma.R
import com.example.khapharma.models.AllStock
import com.example.khapharma.models.AllStockItem
import com.example.khapharma.service.RetrofitService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

@DelicateCoroutinesApi
class CustomAdapter(private val mlist: ArrayList<AllStockItem>, var service: RetrofitService):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    var listeDAta:MutableList<AllStock> = mlist as MutableList<AllStock>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.miseenform,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteStock(id:Int, position: Int){
        GlobalScope.launch {
            listeDAta.removeAt(position)
            service.deleStock(id)
        }
        notifyDataSetChanged()
        notifyItemChanged(id)
        notifyItemRemoved(id)
    }

    class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView) {
        val nomproduit : AppCompatTextView = itemView.findViewById(R.id.nomproduit)
        val quantite : AppCompatTextView = itemView.findViewById(R.id.quante)
        val date : AppCompatTextView = itemView.findViewById(R.id.date)
        val bouton: AppCompatButton = itemView.findViewById(R.id.sup)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mlist[position]
        Log.e("CREATE ITEM", "onBindViewHolder: $ItemsViewModel")
        holder.nomproduit.text = "nom : " + ItemsViewModel.produit
        holder.quantite.text = "quantite : " + ItemsViewModel.qteEntrer
        holder.date.text = "date : " + ItemsViewModel.date

        holder.bouton.setOnClickListener{
            deleteStock(ItemsViewModel.id,position)



    }
    }

    override fun getItemCount(): Int {
        return  mlist.size;
    }

}