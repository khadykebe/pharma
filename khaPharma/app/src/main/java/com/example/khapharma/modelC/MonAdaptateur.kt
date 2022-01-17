package com.example.khapharma.modelC

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khapharma.R
import com.example.khapharma.models.AllUser
import com.example.khapharma.models.AllUserItem
import com.example.khapharma.service.RetrofitService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class MonAdaptateur(private val listeUser:ArrayList<AllUserItem>, var service:RetrofitService):
    RecyclerView.Adapter<MonAdaptateur.ViewHolder>(){
    var listeData :MutableList<AllUser> = listeUser as MutableList<AllUser>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonAdaptateur.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.unproduit,parent,false)
        return MonAdaptateur.ViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
        fun deleteUSer(id:Int, position: Int){
            GlobalScope.launch {
                listeData.removeAt(position)
                service.deleteUser(id)

            }
//            notifyDataSetChanged()
//            notifyItemChanged(id)
//            notifyItemRemoved(id)
    }

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val nom : AppCompatTextView = itemView.findViewById(R.id.nom)
        val prenom : AppCompatTextView = itemView.findViewById(R.id.prenom)
        val role : AppCompatTextView = itemView.findViewById(R.id.role)
        val bouton: AppCompatButton = itemView.findViewById(R.id.sup)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = listeUser[position]
        Log.e("CREATE ITEM", "onBindViewHolder: $ItemsViewModel")
        holder.nom.text = "nom : " + ItemsViewModel.nom
        holder.prenom.text = "prenom : " + ItemsViewModel.prenom
        holder.role.text = "role : " + ItemsViewModel.roles
        holder.bouton.setOnClickListener {
            deleteUSer(ItemsViewModel.id, position)
        }

    }

    override fun getItemCount(): Int {
        return listeUser.size;
    }




}
