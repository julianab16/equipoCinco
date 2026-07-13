package com.desarrollodpmoviles.picobotelladapdm.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.desarrollodpmoviles.picobotelladapdm.databinding.ItemRetoBinding
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import com.desarrollodpmoviles.picobotelladapdm.ui.viewholder.RetoViewHolder

class RetoAdapter (private val listReto:MutableList<Reto>):RecyclerView.Adapter<RetoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetoViewHolder {
        val binding = ItemRetoBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RetoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listReto.size
    }

    override fun onBindViewHolder(holder: RetoViewHolder, position: Int) {
        val reto = listReto[position]
        holder.setItemReto(reto)
    }

    fun actualizarLista(nuevaLista: MutableList<Reto>) {
        listReto.clear()
        listReto.addAll(nuevaLista)
        notifyDataSetChanged()
    }

}